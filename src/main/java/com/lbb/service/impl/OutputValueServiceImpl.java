package com.lbb.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lbb.bean.dto.OutputValueDetailDTO;
import com.lbb.bean.req.OutputValueEditAddReq;
import com.lbb.bean.req.OutputValueIdReq;
import com.lbb.bean.req.OutputValueListReq;
import com.lbb.bean.resp.ApiResp;
import com.lbb.bean.resp.PageResp;
import com.lbb.bean.vo.OutputValueDetailVO;
import com.lbb.bean.vo.OutputValueVO;
import com.lbb.dao.OutputValueDetailModelMapper;
import com.lbb.dao.OutputValueModelMapper;
import com.lbb.dao.SubContractorModelMapper;
import com.lbb.enums.ExceptionCodeEnum;
import com.lbb.exception.BusinessException;
import com.lbb.model.InvoiceInfoModel;
import com.lbb.model.OutputValueDetailModel;
import com.lbb.model.OutputValueModel;
import com.lbb.model.SubContractorModel;
import com.lbb.service.OutputValueService;
import com.lbb.utils.BeanUtils;
import com.lbb.utils.TokenHelper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OutputValueServiceImpl implements OutputValueService {

    @Resource
    private OutputValueModelMapper outputValueModelMapper;

    @Resource
    private SubContractorModelMapper subContractorModelMapper;

    @Resource
    private OutputValueDetailModelMapper outputValueDetailModelMapper;

    @Transactional
    @Override
    public ApiResp outputValueAdd(OutputValueEditAddReq req) {
        OutputValueModel outputValueModel = BeanUtils.convert(req, OutputValueModel.class);
        outputValueModel.setCreateUserId(TokenHelper.getCurrentUser().getCreateUserId());
        outputValueModelMapper.insertSelective(outputValueModel);

        saveOutputValueDetailBatch(req, outputValueModel);
        return ApiResp.success();
    }

    @Transactional
    @Override
    public ApiResp outputValueEdit(OutputValueEditAddReq req) {
        OutputValueModel outputValueModel = outputValueModelMapper.selectByPrimaryKey(req.getOutputValueId());
        if (outputValueModel == null) {
            throw new BusinessException(ExceptionCodeEnum.OUTPUT_VALUE_NOT_EXIST);
        }

        outputValueModel = BeanUtils.convert(req, OutputValueModel.class);
        outputValueModelMapper.updateByPrimaryKeySelective(outputValueModel);

        outputValueDetailModelMapper.deleteByOutputId(req.getOutputValueId());

        saveOutputValueDetailBatch(req, outputValueModel);
        return ApiResp.success();
    }

    private void saveOutputValueDetailBatch(OutputValueEditAddReq req, OutputValueModel outputValueModel) {
        for (OutputValueDetailDTO outputValueDetailDTO : req.getOutputValueDetailList()) {
            OutputValueDetailModel detailModel = new OutputValueDetailModel();
            detailModel.setOutputValueId(outputValueModel.getOutputValueId());
            detailModel.setCreateUserId(TokenHelper.getCurrentUser().getCreateUserId());
            detailModel.setSubContractorId(outputValueDetailDTO.getSubContractorId());

            detailModel.setSubContractorAmount(outputValueDetailDTO.getSubContractorAmount());
            BigDecimal rate = outputValueDetailDTO.getSubContractorAmount().divide(req.getOutputValueAmount(), 4, RoundingMode.CEILING);
            detailModel.setOutputValueRate(rate.multiply(new BigDecimal("100")));
            detailModel.setMigrantWorkerAmount(rate.multiply(req.getMigrantWorkerAmount()));
            outputValueDetailModelMapper.insertSelective(detailModel);
        }
    }

    @Override
    public ApiResp outputValueList(OutputValueListReq req) {
        List<OutputValueVO> outputValueVOList = new ArrayList<>();

        Page<InvoiceInfoModel> page = PageHelper.startPage(req.getPageIndex(), req.getPageSize());
        List<OutputValueModel> outputValueModelList = outputValueModelMapper.selectByParams(req.getOutputValueStartTime(), req.getOutputValueEndTime());
        if (CollectionUtils.isNotEmpty(outputValueModelList)) {
            outputValueVOList = outputValueModelList.parallelStream().map(outputValueModel -> {
                OutputValueVO outputValueVO = BeanUtils.convert(outputValueModel, OutputValueVO.class);

                List<OutputValueDetailModel> outputValueDetailModelList = outputValueDetailModelMapper.selectListByOutputValueId(outputValueModel.getOutputValueId());
                List<OutputValueDetailVO> detailVOList = BeanUtils.convertList(outputValueDetailModelList, OutputValueDetailVO.class);
                detailVOList = detailVOList.stream()
                        .sorted(Comparator.comparingLong(OutputValueDetailVO::getSubContractorId))
                        .collect(Collectors.toList());

                for (OutputValueDetailVO detailVO : detailVOList) {
                    SubContractorModel subContractorModel = subContractorModelMapper.selectByPrimaryKey(detailVO.getSubContractorId());
                    detailVO.setSubContractorName(subContractorModel.getSubContractorName());
                }

                outputValueVO.setOutputValueDetailList(detailVOList);
                return outputValueVO;
            }).collect(Collectors.toList());
        }

        PageResp<OutputValueVO> pageResp = new PageResp<>(outputValueVOList, page.getTotal());

        // 分包平摊总数
        Map<String, BigDecimal> sumMap = new HashMap<>();
        for (int i = 0; i < 7; i++) {
            sumMap.put("subContractor" + i, BigDecimal.ZERO);
            sumMap.put("migrantWorker" + i, BigDecimal.ZERO);
        }
        sumMap.put("subContractorSum", BigDecimal.ZERO);
        sumMap.put("migrantWorkerSum", BigDecimal.ZERO);

        outputValueModelList = outputValueModelMapper.selectByParams(req.getOutputValueStartTime(), req.getOutputValueEndTime());
        outputValueModelList.forEach(outputValueModel -> {
            List<OutputValueDetailModel> outputValueDetailModelList = outputValueDetailModelMapper.selectListByOutputValueId(outputValueModel.getOutputValueId());

            if (CollectionUtils.isNotEmpty(outputValueDetailModelList)) {
                outputValueDetailModelList.stream()
                        .sorted(Comparator.comparingInt(OutputValueDetailModel::getSubContractorId))
                        .forEach(item -> {
                            String key = "subContractor" + (item.getSubContractorId() - 1);
                            sumMap.put(key, sumMap.get(key).add(item.getSubContractorAmount()));

                            key = "migrantWorker" + (item.getSubContractorId() - 1);
                            sumMap.put(key, sumMap.get(key).add(item.getMigrantWorkerAmount()));
                        });
            }

            sumMap.put("subContractorSum", sumMap.get("subContractorSum").add(outputValueModel.getSubContractorAmount()));
            sumMap.put("migrantWorkerSum", sumMap.get("migrantWorkerSum").add(outputValueModel.getMigrantWorkAmount()));
        });

        pageResp.setSumMap(sumMap);
        return ApiResp.successWithObj(pageResp);
    }

    @Transactional
    @Override
    public ApiResp outputValueDelete(OutputValueIdReq req) {
        outputValueModelMapper.deleteByPrimaryKey(req.getOutputValueId());
        outputValueDetailModelMapper.deleteByOutputId(req.getOutputValueId());
        return ApiResp.success();
    }
}
