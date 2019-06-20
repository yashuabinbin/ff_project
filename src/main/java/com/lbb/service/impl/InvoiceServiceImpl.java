package com.lbb.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lbb.bean.req.InvoiceEditAddReq;
import com.lbb.bean.req.InvoiceIdReq;
import com.lbb.bean.req.InvoiceInfoListReq;
import com.lbb.bean.resp.ApiResp;
import com.lbb.bean.resp.PageResp;
import com.lbb.bean.vo.InvoiceDetailInfoVO;
import com.lbb.bean.vo.InvoiceInfoVO;
import com.lbb.dao.ContractModelMapper;
import com.lbb.dao.InvoiceDetailInfoModelMapper;
import com.lbb.dao.InvoiceInfoModelMapper;
import com.lbb.dao.SubContractorModelMapper;
import com.lbb.model.ContractModel;
import com.lbb.model.InvoiceDetailInfoModel;
import com.lbb.model.InvoiceInfoModel;
import com.lbb.model.SubContractorModel;
import com.lbb.service.InvoiceService;
import com.lbb.utils.BeanUtils;
import com.lbb.utils.MyCollectionUtils;
import com.lbb.utils.TokenHelper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.ROUND_HALF_DOWN;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Resource
    private ContractModelMapper contractModelMapper;

    @Resource
    private SubContractorModelMapper subContractorModelMapper;

    @Resource
    private InvoiceInfoModelMapper invoiceInfoModelMapper;

    @Resource
    private InvoiceDetailInfoModelMapper invoiceDetailInfoModelMapper;

    @Override
    public ApiResp invoiceInfoList(InvoiceInfoListReq req) {
        List<Integer> contractNumSearchContractIdList = null;
        List<Integer> subContractorSearchContractIdList = null;
        List<Integer> searchContractIdList = null;
        if (StringUtils.isNotBlank(req.getContractNum())) {
            contractNumSearchContractIdList = contractModelMapper.selectContractIdListByContractNum(req.getContractNum());
        }

        if (req.getSubContractorId() != null) {
            subContractorSearchContractIdList = invoiceDetailInfoModelMapper.selectContractIdListBySubContractorId(req.getSubContractorId());
        }

        if (contractNumSearchContractIdList != null && subContractorSearchContractIdList != null) {
            searchContractIdList = MyCollectionUtils.intersection(contractNumSearchContractIdList, subContractorSearchContractIdList);
        } else {
            if (contractNumSearchContractIdList != null) {
                searchContractIdList = contractNumSearchContractIdList;
            } else {
                searchContractIdList = subContractorSearchContractIdList;
            }
        }

        if (searchContractIdList != null && searchContractIdList.size() == 0) {
            return ApiResp.successWithObj(new PageResp<>(new ArrayList<>(), 0L));
        }

        List<InvoiceInfoVO> invoiceInfoVOList = new ArrayList<>();

        Page<InvoiceInfoModel> page = PageHelper.startPage(req.getPageIndex(), req.getPageSize());
        List<InvoiceInfoModel> invoiceInfoModelList = invoiceInfoModelMapper.selectListByParams(searchContractIdList, req.getInvoiceStartTime(), req.getInvoiceEndTime());
        if (CollectionUtils.isNotEmpty(invoiceInfoModelList)) {
            invoiceInfoModelList.forEach(invoiceInfoModel -> {
                List<InvoiceDetailInfoVO> invoiceDetailInfoVOList = new ArrayList<>();

                List<InvoiceDetailInfoModel> invoiceDetailInfoModelList = invoiceDetailInfoModelMapper.selectListByInvoiceId(invoiceInfoModel.getInvoiceId());
                if (CollectionUtils.isNotEmpty(invoiceDetailInfoModelList)) {
                    invoiceDetailInfoModelList.forEach(invoiceDetailInfoModel -> {
                        InvoiceDetailInfoVO invoiceDetailInfoVO = BeanUtils.convert(invoiceDetailInfoModel, InvoiceDetailInfoVO.class);
                        SubContractorModel subContractorModel = subContractorModelMapper.selectByPrimaryKey(invoiceDetailInfoModel.getSubContractorId());
                        if (subContractorModel != null) {
                            invoiceDetailInfoVO.setSubContractorName(subContractorModel.getSubContractorName());
                        }

                        invoiceDetailInfoVOList.add(invoiceDetailInfoVO);
                    });
                }
                InvoiceInfoVO invoiceInfoVO = BeanUtils.convert(invoiceInfoModel, InvoiceInfoVO.class);
                invoiceInfoVO.setInvoiceDetailInfoList(invoiceDetailInfoVOList);

                ContractModel contractModel = contractModelMapper.selectByPrimaryKey(invoiceInfoModel.getContractId());
                if (contractModel != null) {
                    invoiceInfoVO.setContractName(contractModel.getContractName());
                    invoiceInfoVO.setContractNum(contractModel.getContractNum());
                }

                invoiceInfoVOList.add(invoiceInfoVO);
            });
        }

        PageResp<InvoiceInfoVO> pageResp = new PageResp<>();
        pageResp.setList(invoiceInfoVOList);
        pageResp.setTotal(page.getTotal());
        return ApiResp.successWithObj(pageResp);
    }

    @Transactional
    @Override
    public ApiResp invoiceInfoAdd(InvoiceEditAddReq req) {
        InvoiceInfoModel invoiceInfoModel = BeanUtils.convert(req, InvoiceInfoModel.class);
        invoiceInfoModel.setCreateUserId(TokenHelper.getCurrentUser().getUserId());
        invoiceInfoModel.setDeductAmount(calculateDeductAmount(invoiceInfoModel.getBeforeTaxAmount(), invoiceInfoModel.getTaxRate()));
        invoiceInfoModelMapper.insertSelective(invoiceInfoModel);

        saveInvoiceInfoBatch(req, invoiceInfoModel);
        return ApiResp.success();
    }


    @Transactional
    @Override
    public ApiResp invoiceInfoEdit(InvoiceEditAddReq req) {
        InvoiceInfoModel invoiceInfoModel = BeanUtils.convert(req, InvoiceInfoModel.class);
        invoiceInfoModel.setDeductAmount(calculateDeductAmount(invoiceInfoModel.getBeforeTaxAmount(), invoiceInfoModel.getTaxRate()));
        invoiceInfoModelMapper.updateByPrimaryKeySelective(invoiceInfoModel);

        invoiceDetailInfoModelMapper.deleteByInvoiceId(req.getInvoiceId());
        saveInvoiceInfoBatch(req, invoiceInfoModel);
        return ApiResp.success();
    }

    private BigDecimal calculateDeductAmount(BigDecimal beforeTaxAmount, BigDecimal taxRate) {
        BigDecimal bg100 = new BigDecimal("100");
        BigDecimal bg1 = new BigDecimal("1");
        return beforeTaxAmount.divide(bg1.add(taxRate.divide(bg100, 4, ROUND_HALF_DOWN)), 4, ROUND_HALF_DOWN).multiply(taxRate.divide(bg100, 4, ROUND_HALF_DOWN));
    }

    private void saveInvoiceInfoBatch(InvoiceEditAddReq req, InvoiceInfoModel invoiceInfoModel) {
        if (CollectionUtils.isNotEmpty(req.getInvoiceDetailInfoList())) {
            List<InvoiceDetailInfoModel> invoiceDetailInfoModelList = BeanUtils.convertList(req.getInvoiceDetailInfoList(), InvoiceDetailInfoModel.class);
            invoiceDetailInfoModelList.forEach(item -> {
                item.setInvoiceId(invoiceInfoModel.getInvoiceId());
                item.setContractId(invoiceInfoModel.getContractId());
                item.setCreateUserId(TokenHelper.getCurrentUser().getUserId());
                item.setShareAmount(req.getBeforeTaxAmount().multiply(item.getShareRate()).divide(new BigDecimal("100")));
                invoiceDetailInfoModelMapper.insertSelective(item);
            });
        }
    }


    @Transactional
    @Override
    public ApiResp invoiceDelete(InvoiceIdReq req) {
        invoiceInfoModelMapper.deleteByPrimaryKey(req.getInvoiceId());
        invoiceDetailInfoModelMapper.deleteByInvoiceId(req.getInvoiceId());
        return ApiResp.success();
    }


    @Override
    public ApiResp listAllInvoiceContent() {
        List<String> invoiceContentList = invoiceInfoModelMapper.selectInvoiceContent();
        return ApiResp.successWithObj(invoiceContentList);
    }
}
