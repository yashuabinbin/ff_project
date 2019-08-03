package com.lbb.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lbb.bean.req.ContractIdReq;
import com.lbb.bean.req.SubContractorEditAddReq;
import com.lbb.bean.req.SubContractorIdReq;
import com.lbb.bean.req.SubContractorListReq;
import com.lbb.bean.resp.ApiResp;
import com.lbb.bean.resp.PageResp;
import com.lbb.bean.vo.SubContractorVO;
import com.lbb.bean.vo.SubContractorWithShareRateVO;
import com.lbb.dao.ContractSubContractorRelationModelMapper;
import com.lbb.dao.InvoiceDetailInfoModelMapper;
import com.lbb.dao.PayDetailInfoModelMapper;
import com.lbb.dao.SubContractorModelMapper;
import com.lbb.enums.ExceptionCodeEnum;
import com.lbb.exception.BusinessException;
import com.lbb.model.ContractSubContractorRelationModel;
import com.lbb.model.InvoiceDetailInfoModel;
import com.lbb.model.PayDetailInfoModel;
import com.lbb.model.SubContractorModel;
import com.lbb.service.SubContractorService;
import com.lbb.utils.BeanUtils;
import com.lbb.utils.TokenHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SubContractorServiceImpl implements SubContractorService {

    @Resource
    private SubContractorModelMapper subContractorModelMapper;

    @Resource
    private ContractSubContractorRelationModelMapper contractSubContractorRelationModelMapper;

    @Resource
    private PayDetailInfoModelMapper payDetailInfoModelMapper;

    @Resource
    private InvoiceDetailInfoModelMapper invoiceDetailInfoModelMapper;

    @Override
    public ApiResp subContractorList(SubContractorListReq req) {
        List<SubContractorVO> subContractorVOList = new ArrayList<>();

        Page<Object> page = PageHelper.startPage(req.getPageIndex(), req.getPageSize());
        List<SubContractorModel> subContractorModelList = subContractorModelMapper.selectListByName(req.getSubContractorName());
        if (CollectionUtils.isNotEmpty(subContractorModelList)) {
            subContractorVOList = BeanUtils.convertList(subContractorModelList, SubContractorVO.class);
        }

        PageResp<SubContractorVO> pageResp = new PageResp<>();
        pageResp.setList(subContractorVOList);
        pageResp.setTotal(page.getTotal());
        return ApiResp.successWithObj(pageResp);
    }

    @Override
    public ApiResp subContractorAdd(SubContractorEditAddReq req) {
        List<SubContractorModel> existedSubContractorList = subContractorModelMapper.selectListByName(req.getSubContractorName());
        if (CollectionUtils.isNotEmpty(existedSubContractorList)) {
            throw new BusinessException(ExceptionCodeEnum.SUB_CONTRACTOR_NAME_EXISTED);
        }

        SubContractorModel subContractorModel = BeanUtils.convert(req, SubContractorModel.class);
        subContractorModel.setCreateUserId(TokenHelper.getCurrentUser().getUserId());
        subContractorModelMapper.insertSelective(subContractorModel);
        return ApiResp.success();
    }

    @Override
    public ApiResp subContractorEdit(SubContractorEditAddReq req) {
        List<SubContractorModel> existedSubContractorList = subContractorModelMapper.selectListByName(req.getSubContractorName());
        if (CollectionUtils.isNotEmpty(existedSubContractorList)) {
            Optional<SubContractorModel> existedOptional = existedSubContractorList.parallelStream()
                    .filter(item -> !item.getSubContractorId().equals(req.getSubContractorId()))
                    .findFirst();
            if (existedOptional.isPresent()) {
                throw new BusinessException(ExceptionCodeEnum.SUB_CONTRACTOR_NAME_EXISTED);
            }
        }

        SubContractorModel contractModel = subContractorModelMapper.selectByPrimaryKey(req.getSubContractorId());
        if (contractModel == null) {
            throw new BusinessException(ExceptionCodeEnum.CONTRACT_NOT_EXIST);
        }

        contractModel = BeanUtils.convert(req, SubContractorModel.class);
        subContractorModelMapper.updateByPrimaryKeySelective(contractModel);
        return ApiResp.success();
    }

    @Transactional
    @Override
    public ApiResp subContractorDelete(SubContractorIdReq req) {
        // 检查是否能够删除
        List<PayDetailInfoModel> payDetailInfoModelList = payDetailInfoModelMapper.selectListBySubContractorId(req.getSubContractorId());
        List<InvoiceDetailInfoModel> invoiceDetailInfoModelList = invoiceDetailInfoModelMapper.selectListBySubContractorId(req.getSubContractorId());

        if (CollectionUtils.isNotEmpty(payDetailInfoModelList)) {
            throw new BusinessException(ExceptionCodeEnum.PAY_DETAIL_SUB_CONTRACTOR_ID_EXIST);
        }

        if (CollectionUtils.isNotEmpty(invoiceDetailInfoModelList)) {
            throw new BusinessException(ExceptionCodeEnum.INVOICE_DETAIL_SUB_CONTRACTOR_ID_EXIST);
        }

        subContractorModelMapper.deleteByPrimaryKey(req.getSubContractorId());
        contractSubContractorRelationModelMapper.deleteBySubContractorId(req.getSubContractorId());
        return ApiResp.success();
    }

    @Override
    public ApiResp subContractorListAll() {
        List<SubContractorModel> subContractorModelList = subContractorModelMapper.selectAll();
        return ApiResp.successWithObj(BeanUtils.convertList(subContractorModelList, SubContractorVO.class));
    }

    @Override
    public ApiResp getSubContractorWithShareRate(ContractIdReq req) {
        List<SubContractorWithShareRateVO> subContractorWithShareRateVOList = new ArrayList<>();

        // 获取所有分包
        List<SubContractorModel> subContractorModelList = subContractorModelMapper.selectAll();

        // 封装返回体
        List<ContractSubContractorRelationModel> relationModelList = contractSubContractorRelationModelMapper.selectListByContractId(req.getContractId());
        if (CollectionUtils.isNotEmpty(relationModelList)) {
            relationModelList.forEach(relation -> {
                SubContractorWithShareRateVO subContractorWithShareRateVO = new SubContractorWithShareRateVO();
                subContractorWithShareRateVO.setSubContractorAmount(relation.getSubContractorAmount());
                subContractorWithShareRateVO.setSubContractorId(relation.getSubContractorId());
                subContractorModelList.parallelStream()
                        .filter(subContractorModel -> subContractorModel.getSubContractorId().equals(relation.getSubContractorId()))
                        .findFirst()
                        .ifPresent(subContractorModel -> subContractorWithShareRateVO.setSubContractorName(subContractorModel.getSubContractorName()));
                subContractorWithShareRateVOList.add(subContractorWithShareRateVO);
            });
        }

        return ApiResp.successWithObj(subContractorWithShareRateVOList);
    }
}
