package com.lbb.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lbb.bean.dto.SubContractorDTO;
import com.lbb.bean.req.ContractEditAddReq;
import com.lbb.bean.req.ContractIdReq;
import com.lbb.bean.req.ContractListReq;
import com.lbb.bean.resp.ApiResp;
import com.lbb.bean.resp.PageResp;
import com.lbb.bean.vo.ContractVO;
import com.lbb.bean.vo.SubContractorWithShareRateVO;
import com.lbb.dao.*;
import com.lbb.enums.ExceptionCodeEnum;
import com.lbb.exception.BusinessException;
import com.lbb.model.*;
import com.lbb.service.ContractService;
import com.lbb.utils.BeanUtils;
import com.lbb.utils.TokenHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ContractServiceImpl implements ContractService {

    @Resource
    private PayInfoModelMapper payInfoModelMapper;

    @Resource
    private ContractModelMapper contractModelMapper;

    @Resource
    private InvoiceInfoModelMapper invoiceInfoModelMapper;

    @Resource
    private SubContractorModelMapper subContractorModelMapper;

    @Resource
    private ContractSubContractorRelationModelMapper contractSubContractorRelationModelMapper;

    @Transactional
    @Override
    public ApiResp<PageResp<ContractVO>> contractList(ContractListReq req) {
        List<ContractVO> contractVOList = new ArrayList<>();

        // 所有分包
        List<SubContractorModel> subContractorModelList = subContractorModelMapper.selectAll();

        Page<Object> page = PageHelper.startPage(req.getPageIndex(), req.getPageSize());
        List<ContractModel> contractModelList = contractModelMapper.selectListByNameAndNum(req.getContractName(), req.getContractNum());
        if (CollectionUtils.isNotEmpty(contractModelList)) {
            contractModelList.forEach(contractModel -> {
                ContractVO contractVO = BeanUtils.convert(contractModel, ContractVO.class);
                List<SubContractorWithShareRateVO> rateVOList = new ArrayList<>(7);
                for (int i = 0; i < 7; i++) {
                    rateVOList.add(null);
                }

                // 获取所有关联的分包分摊列表
                List<ContractSubContractorRelationModel> relationModelList =
                        contractSubContractorRelationModelMapper.selectListByContractId(contractModel.getContractId());

                relationModelList.forEach(relationModel -> {
                    SubContractorWithShareRateVO rateVO = new SubContractorWithShareRateVO();
                    subContractorModelList.parallelStream()
                            .filter(item -> item.getSubContractorId().equals(relationModel.getSubContractorId()))
                            .findFirst()
                            .ifPresent(item -> rateVO.setSubContractorName(item.getSubContractorName()));
                    rateVO.setSubContractorId(relationModel.getSubContractorId());
                    rateVO.setSubContractorAmount(relationModel.getSubContractorAmount());
                    rateVOList.set(relationModel.getSubContractorId() - 1, rateVO);
                });
                contractVO.setSubContractorList(rateVOList);
                contractVOList.add(contractVO);
            });
        }

        Map<String, BigDecimal> sumMap = new HashMap<>();
        List<Integer> contractIdList = this.contractModelMapper.selectContractorIdListByParams(req.getContractName(), req.getContractNum());
        List<BigDecimal> subContractorAmountSumList = this.contractSubContractorRelationModelMapper.selectSubContractorAmountSum(contractIdList);
        for (int i = 1; i <= 7; i++) {
            sumMap.put("subContractor" + i, subContractorAmountSumList.get(i - 1));
        }

        BigDecimal contractAmountSum = this.contractModelMapper.selectContractAmountSum(contractIdList);
        sumMap.put("contractAmount", contractAmountSum);

        PageResp<ContractVO> pageResp = new PageResp<>();
        pageResp.setList(contractVOList);
        pageResp.setTotal(page.getTotal());
        pageResp.setSumMap(sumMap);
        return ApiResp.successWithObj(pageResp);
    }

    @Transactional
    @Override
    public ApiResp contractAdd(ContractEditAddReq req) {
        // 校验参数
        List<ContractModel> sameContractNumList = contractModelMapper.selectListByContractNum(req.getContractNum());
        if (CollectionUtils.isNotEmpty(sameContractNumList)) {
            throw new BusinessException(ExceptionCodeEnum.CONTRACT_NUM_EXISTED);
        }

        List<ContractModel> sameContractNameList = contractModelMapper.selectListByContractName(req.getContractName());
        if (CollectionUtils.isNotEmpty(sameContractNameList)) {
            throw new BusinessException(ExceptionCodeEnum.CONTRACT_NAME_EXISTED);
        }

        ContractModel contractModel = BeanUtils.convert(req, ContractModel.class);
        contractModel.setCreateUserId(TokenHelper.getCurrentUser().getUserId());

        contractModelMapper.insertSelective(contractModel);

        // 添加分包列表
        if (CollectionUtils.isNotEmpty(req.getSubContractorList())) {
            saveContractSubContractorRelationList(req, contractModel.getContractId());
        }
        return ApiResp.success();
    }

    @Transactional
    @Override
    public ApiResp contractEdit(ContractEditAddReq req) {
        ContractModel contractModel = contractModelMapper.selectByPrimaryKey(req.getContractId());
        if (contractModel == null) {
            throw new BusinessException(ExceptionCodeEnum.CONTRACT_NOT_EXIST);
        }

        // 校验参数
        List<ContractModel> sameContractNumList = contractModelMapper.selectListByContractNum(req.getContractNum());
        if (CollectionUtils.isNotEmpty(sameContractNumList)) {
            sameContractNumList.parallelStream()
                    .filter(item -> !item.getContractId().equals(req.getContractId()))
                    .findFirst()
                    .ifPresent(item -> {
                        throw new BusinessException(ExceptionCodeEnum.CONTRACT_NUM_EXISTED);
                    });
        }

        List<ContractModel> sameContractNameList = contractModelMapper.selectListByContractName(req.getContractName());
        if (CollectionUtils.isNotEmpty(sameContractNameList)) {
            sameContractNameList.parallelStream()
                    .filter(item -> !item.getContractId().equals(req.getContractId()))
                    .findFirst()
                    .ifPresent(item -> {
                        throw new BusinessException(ExceptionCodeEnum.CONTRACT_NAME_EXISTED);
                    });
        }

        contractModel = BeanUtils.convert(req, ContractModel.class);
        contractModelMapper.updateByPrimaryKeySelective(contractModel);

        if (CollectionUtils.isNotEmpty(req.getSubContractorList())) {
            contractSubContractorRelationModelMapper.deleteByContractId(contractModel.getContractId());
            saveContractSubContractorRelationList(req, contractModel.getContractId());
        }
        return ApiResp.success();
    }

    /**
     * @description: 保存合同和分包关联
     * @param:
     * @return:
     * @author: lubingbin
     * @date: 2019/6/7 4:26 PM
     **/
    private void saveContractSubContractorRelationList(ContractEditAddReq req, Integer contractId) {
        for (SubContractorDTO subContractorDTO : req.getSubContractorList()) {
            ContractSubContractorRelationModel relationModel = new ContractSubContractorRelationModel();
            relationModel.setContractId(contractId);
            relationModel.setSubContractorAmount(subContractorDTO.getShareRate());
            relationModel.setSubContractorId(subContractorDTO.getSubContractorId());
            relationModel.setCreateUserId(TokenHelper.getCurrentUser().getUserId());
            contractSubContractorRelationModelMapper.insertSelective(relationModel);
        }
    }

    @Override
    public ApiResp contractDelete(ContractIdReq req) {
        List<PayInfoModel> payInfoModelList = payInfoModelMapper.selectListByContractId(req.getContractId());
        if (CollectionUtils.isNotEmpty(payInfoModelList)) {
            throw new BusinessException(ExceptionCodeEnum.PAY_INFO_CONTRACT_ID_EXIST);
        }

        List<InvoiceInfoModel> invoiceInfoModelList = invoiceInfoModelMapper.selectListByContractId(req.getContractId());
        if (CollectionUtils.isNotEmpty(invoiceInfoModelList)) {
            throw new BusinessException(ExceptionCodeEnum.INVOICE_INFO_CONTRACT_ID_EXIST);
        }

        contractModelMapper.deleteByPrimaryKey(req.getContractId());
        return ApiResp.success();
    }

    @Override
    public ApiResp  contractListAll() {
        List<ContractModel> contractModelList = contractModelMapper.selectAll();
        List<ContractVO> contractVOList = BeanUtils.convertList(contractModelList, ContractVO.class);
        return ApiResp.successWithObj(contractVOList);
    }
}
