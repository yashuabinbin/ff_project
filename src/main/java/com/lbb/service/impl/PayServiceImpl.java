package com.lbb.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lbb.bean.req.PayEditAddReq;
import com.lbb.bean.req.PayIdReq;
import com.lbb.bean.req.PayInfoListReq;
import com.lbb.bean.resp.ApiResp;
import com.lbb.bean.resp.PageResp;
import com.lbb.bean.vo.PayDetailInfoVO;
import com.lbb.bean.vo.PayInfoVO;
import com.lbb.dao.*;
import com.lbb.model.*;
import com.lbb.service.PayService;
import com.lbb.utils.BeanUtils;
import com.lbb.utils.MyCollectionUtils;
import com.lbb.utils.TokenHelper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PayServiceImpl implements PayService {

    @Resource
    private PayInfoModelMapper payInfoModelMapper;

    @Resource
    private ContractModelMapper contractModelMapper;

    @Resource
    private SubContractorModelMapper subContractorModelMapper;

    @Resource
    private PayDetailInfoModelMapper payDetailInfoModelMapper;

    @Resource
    private ContractSubContractorRelationModelMapper contractSubContractorRelationModelMapper;

    @Transactional
    @Override
    public ApiResp payInfoAdd(PayEditAddReq req) {
        PayInfoModel payInfoModel = BeanUtils.convert(req, PayInfoModel.class);
        payInfoModel.setCreateUserId(TokenHelper.getCurrentUser().getUserId());
        payInfoModelMapper.insertSelective(payInfoModel);

        savePayInfoBatch(req, payInfoModel);
        return ApiResp.success();
    }

    @Transactional
    @Override
    public ApiResp payInfoEdit(PayEditAddReq req) {
        PayInfoModel payInfoModel = BeanUtils.convert(req, PayInfoModel.class);
        payInfoModelMapper.updateByPrimaryKeySelective(payInfoModel);

        payDetailInfoModelMapper.deleteByPayId(req.getPayId());

        savePayInfoBatch(req, payInfoModel);
        return ApiResp.success();
    }

    @Transactional
    @Override
    public ApiResp payDelete(PayIdReq req) {
        payInfoModelMapper.deleteByPrimaryKey(req.getPayId());
        payDetailInfoModelMapper.deleteByPayId(req.getPayId());
        return ApiResp.success();
    }

    @Override
    public ApiResp listAllPayee() {
        List<String> payeeList = payInfoModelMapper.selectPayeeList();
        return ApiResp.successWithObj(payeeList);
    }

    private void savePayInfoBatch(PayEditAddReq req, PayInfoModel payInfoModel) {
        if (CollectionUtils.isNotEmpty(req.getPayDetailInfoList())) {
            List<PayDetailInfoModel> payDetailInfoModelList = BeanUtils.convertList(req.getPayDetailInfoList(), PayDetailInfoModel.class);
            payDetailInfoModelList.forEach(item -> {
                item.setPayId(payInfoModel.getPayId());
                item.setContractId(payInfoModel.getContractId());
                item.setCreateUserId(TokenHelper.getCurrentUser().getUserId());
                item.setSubContractorAmount(item.getSubContractorAmount());
                payDetailInfoModelMapper.insertSelective(item);
            });
        }
    }

    @Override
    public ApiResp payInfoList(PayInfoListReq req) {
        List<Integer> contractNumSearchContractIdList = null;
        List<Integer> searchContractIdList = null;
        if (StringUtils.isNotBlank(req.getContractNum())) {
            contractNumSearchContractIdList = contractModelMapper.selectContractIdListByContractNum(req.getContractNum());

            if (CollectionUtils.isEmpty(contractNumSearchContractIdList)) {
                return ApiResp.successWithObj(new PageResp<>(new ArrayList<>(), 0L));
            }
        }

        List<PayInfoVO> payInfoVOList = new ArrayList<>();

        Page<PayInfoModel> page = PageHelper.startPage(req.getPageIndex(), req.getPageSize());
        List<PayInfoModel> payInfoModelList = payInfoModelMapper.selectListByParams(searchContractIdList, req.getPayee(), req.getPayType(), req.getPayStartTime(), req.getPayEndTime());
        if (CollectionUtils.isNotEmpty(payInfoModelList)) {
            payInfoModelList.forEach(payInfoModel -> {
                PayInfoVO payInfoVO = BeanUtils.convert(payInfoModel, PayInfoVO.class);

                List<PayDetailInfoModel> payDetailInfoModelList = payDetailInfoModelMapper.selectListByPayId(payInfoModel.getPayId());
                List<PayDetailInfoVO> payDetailInfoVOList = payDetailInfoModelList.parallelStream()
                        .map(payDetailInfoModel -> {
                            PayDetailInfoVO payDetailInfoVO = BeanUtils.convert(payDetailInfoModel, PayDetailInfoVO.class);

                            // 设置分包名称
                            SubContractorModel subContractorModel = subContractorModelMapper.selectByPrimaryKey(payDetailInfoModel.getSubContractorId());
                            if (subContractorModel != null) {
                                payDetailInfoVO.setSubContractorName(subContractorModel.getSubContractorName());
                            }

                            return payDetailInfoVO;
                        })
                        .sorted(Comparator.comparingInt(PayDetailInfoVO::getSubContractorId))
                        .collect(Collectors.toList());

                payInfoVO.setPayDetailInfoList(payDetailInfoVOList);

                ContractModel contractModel = contractModelMapper.selectByPrimaryKey(payInfoModel.getContractId());
                if (contractModel != null) {
                    payInfoVO.setContractName(contractModel.getContractName());
                    payInfoVO.setContractNum(contractModel.getContractNum());
                }

                payInfoVOList.add(payInfoVO);
            });
        }

        // 分包平摊总数
        Map<String, BigDecimal> sumMap = new HashMap<>();
        for (int i = 1; i <= 7; i++) {
            sumMap.put("subContractor" + i, BigDecimal.ZERO);
        }
        sumMap.put("payAmount", BigDecimal.ZERO);
        sumMap.put("performanceBoundAmount", BigDecimal.ZERO);

        payInfoModelList = payInfoModelMapper.selectListByParams(searchContractIdList, req.getPayee(), req.getPayType(), req.getPayStartTime(), req.getPayEndTime());
        payInfoModelList.forEach(payInfoModel -> {
            List<ContractSubContractorRelationModel> relationModelList = contractSubContractorRelationModelMapper.selectListByContractId(payInfoModel.getContractId());

            List<PayDetailInfoModel> payDetailInfoModelList = payDetailInfoModelMapper.selectListByPayId(payInfoModel.getPayId());
            if (CollectionUtils.isNotEmpty(payDetailInfoModelList)) {
                payDetailInfoModelList.forEach(payDetailInfoModel -> {
                    PayDetailInfoVO payDetailInfoVO = BeanUtils.convert(payDetailInfoModel, PayDetailInfoVO.class);

                    // 设置idx
                    relationModelList.parallelStream()
                            .filter(item -> item.getSubContractorId().equals(payDetailInfoModel.getSubContractorId()))
                            .findFirst()
                            .ifPresent(item -> sumMap.put("subContractor" + item.getSubContractorId(), sumMap.get("subContractor" + item.getSubContractorId()).add(payDetailInfoVO.getSubContractorAmount())));
                });
            }

            sumMap.put("payAmount", sumMap.get("payAmount").add(payInfoModel.getPayAmount()));
            sumMap.put("performanceBoundAmount", sumMap.get("performanceBoundAmount").add(payInfoModel.getPerformanceBoundAmount()));
        });

        PageResp<PayInfoVO> pageResp = new PageResp<>();
        pageResp.setList(payInfoVOList);
        pageResp.setTotal(page.getTotal());
        pageResp.setSumMap(sumMap);
        return ApiResp.successWithObj(pageResp);
    }
}
