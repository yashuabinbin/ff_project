package com.lbb.service.impl;

import com.lbb.bean.dto.OutputValueDetailDTO;
import com.lbb.bean.req.OutputAddEditReq;
import com.lbb.bean.req.OutputSearchReq;
import com.lbb.bean.req.OutputValueIdReq;
import com.lbb.bean.resp.ApiResp;
import com.lbb.bean.vo.OutputDetailInfoDifVo;
import com.lbb.bean.vo.OutputValueDetailVO;
import com.lbb.bean.vo.OutputValueVO;
import com.lbb.dao.OutputValueDetailModelMapper;
import com.lbb.dao.OutputValueModelMapper;
import com.lbb.dao.SubContractorModelMapper;
import com.lbb.enums.ExceptionCodeEnum;
import com.lbb.exception.BusinessException;
import com.lbb.model.OutputValueDetailModel;
import com.lbb.model.OutputValueModel;
import com.lbb.service.OutputValueService;
import com.lbb.utils.BeanUtils;
import com.lbb.utils.TokenHelper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class OutputValueServiceImpl implements OutputValueService {

    @Resource
    private OutputValueModelMapper outputValueModelMapper;

    @Resource
    private SubContractorModelMapper subContractorModelMapper;

    @Resource
    private OutputValueDetailModelMapper outputValueDetailModelMapper;

    @Override
    public ApiResp<OutputValueVO> getByOutputTime(OutputSearchReq req) {
        OutputValueVO outputValueVO = null;
        List<OutputValueDetailModel> outputValueDetailModelList = new ArrayList<>();

        if (req.getOutputTime() != null) {
            OutputValueModel outputValueModel = this.outputValueModelMapper.selectByOutputTime(req.getOutputTime());
            if (outputValueModel == null) {
                return ApiResp.success();
            }

            outputValueDetailModelList = this.outputValueDetailModelMapper.selectListByOutputValueId(outputValueModel.getOutputValueId());
            outputValueVO = BeanUtils.convert(outputValueModel, OutputValueVO.class);
        } else {
            outputValueDetailModelList = this.outputValueDetailModelMapper.selectSumList();
            outputValueVO = new OutputValueVO();
        }

        List<OutputDetailInfoDifVo> difVoList = new ArrayList<>();

        OutputDetailInfoDifVo difVo = new OutputDetailInfoDifVo();
        difVo.setName("产值");
        difVo.setValue1(outputValueDetailModelList.get(0).getOutputAmount());
        difVo.setValue2(outputValueDetailModelList.get(1).getOutputAmount());
        difVo.setValue3(outputValueDetailModelList.get(2).getOutputAmount());
        difVo.setValue4(outputValueDetailModelList.get(3).getOutputAmount());
        difVo.setValue5(outputValueDetailModelList.get(4).getOutputAmount());
        difVo.setValue6(outputValueDetailModelList.get(5).getOutputAmount());
        difVo.setValue7(outputValueDetailModelList.get(6).getOutputAmount());
        difVoList.add(difVo);

        OutputDetailInfoDifVo difVo2 = new OutputDetailInfoDifVo();
        difVo2.setName("分部分项");
        difVo2.setValue1(outputValueDetailModelList.get(0).getOutputCategoryAmount());
        difVo2.setValue2(outputValueDetailModelList.get(1).getOutputCategoryAmount());
        difVo2.setValue3(outputValueDetailModelList.get(2).getOutputCategoryAmount());
        difVo2.setValue4(outputValueDetailModelList.get(3).getOutputCategoryAmount());
        difVo2.setValue5(outputValueDetailModelList.get(4).getOutputCategoryAmount());
        difVo2.setValue6(outputValueDetailModelList.get(5).getOutputCategoryAmount());
        difVo2.setValue7(outputValueDetailModelList.get(6).getOutputCategoryAmount());
        difVoList.add(difVo2);

        OutputDetailInfoDifVo difVo3 = new OutputDetailInfoDifVo();
        difVo3.setName("措施费");
        difVo3.setValue1(outputValueDetailModelList.get(0).getOutputStepAmount());
        difVo3.setValue2(outputValueDetailModelList.get(1).getOutputStepAmount());
        difVo3.setValue3(outputValueDetailModelList.get(2).getOutputStepAmount());
        difVo3.setValue4(outputValueDetailModelList.get(3).getOutputStepAmount());
        difVo3.setValue5(outputValueDetailModelList.get(4).getOutputStepAmount());
        difVo3.setValue6(outputValueDetailModelList.get(5).getOutputStepAmount());
        difVo3.setValue7(outputValueDetailModelList.get(6).getOutputStepAmount());
        difVoList.add(difVo3);

        OutputDetailInfoDifVo difVo4 = new OutputDetailInfoDifVo();
        difVo4.setName("收款");
        difVo4.setValue1(outputValueDetailModelList.get(0).getIncomeAmount());
        difVo4.setValue2(outputValueDetailModelList.get(1).getIncomeAmount());
        difVo4.setValue3(outputValueDetailModelList.get(2).getIncomeAmount());
        difVo4.setValue4(outputValueDetailModelList.get(3).getIncomeAmount());
        difVo4.setValue5(outputValueDetailModelList.get(4).getIncomeAmount());
        difVo4.setValue6(outputValueDetailModelList.get(5).getIncomeAmount());
        difVo4.setValue7(outputValueDetailModelList.get(6).getIncomeAmount());
        difVoList.add(difVo4);

        OutputDetailInfoDifVo difVo5 = new OutputDetailInfoDifVo();
        difVo5.setName("分部分项");
        difVo5.setValue1(outputValueDetailModelList.get(0).getIncomeCategoryAmount());
        difVo5.setValue2(outputValueDetailModelList.get(1).getIncomeCategoryAmount());
        difVo5.setValue3(outputValueDetailModelList.get(2).getIncomeCategoryAmount());
        difVo5.setValue4(outputValueDetailModelList.get(3).getIncomeCategoryAmount());
        difVo5.setValue5(outputValueDetailModelList.get(4).getIncomeCategoryAmount());
        difVo5.setValue6(outputValueDetailModelList.get(5).getIncomeCategoryAmount());
        difVo5.setValue7(outputValueDetailModelList.get(6).getIncomeCategoryAmount());
        difVoList.add(difVo5);

        OutputDetailInfoDifVo difVo6 = new OutputDetailInfoDifVo();
        difVo6.setName("措施费");
        difVo6.setValue1(outputValueDetailModelList.get(0).getIncomeStepAmount());
        difVo6.setValue2(outputValueDetailModelList.get(1).getIncomeStepAmount());
        difVo6.setValue3(outputValueDetailModelList.get(2).getIncomeStepAmount());
        difVo6.setValue4(outputValueDetailModelList.get(3).getIncomeStepAmount());
        difVo6.setValue5(outputValueDetailModelList.get(4).getIncomeStepAmount());
        difVo6.setValue6(outputValueDetailModelList.get(5).getIncomeStepAmount());
        difVo6.setValue7(outputValueDetailModelList.get(6).getIncomeStepAmount());
        difVoList.add(difVo6);

        OutputDetailInfoDifVo difVo7 = new OutputDetailInfoDifVo();
        difVo7.setName("应付账款");
        difVo7.setValue1(outputValueDetailModelList.get(0).getPayAmount());
        difVo7.setValue2(outputValueDetailModelList.get(1).getPayAmount());
        difVo7.setValue3(outputValueDetailModelList.get(2).getPayAmount());
        difVo7.setValue4(outputValueDetailModelList.get(3).getPayAmount());
        difVo7.setValue5(outputValueDetailModelList.get(4).getPayAmount());
        difVo7.setValue6(outputValueDetailModelList.get(5).getPayAmount());
        difVo7.setValue7(outputValueDetailModelList.get(6).getPayAmount());
        difVoList.add(difVo7);

        outputValueVO.setDifVoList(difVoList);
        return ApiResp.successWithObj(outputValueVO);
    }

    @Override
    public ApiResp<List<Integer>> getOutputTimeList() {
        List<Integer> outputTimeList = this.outputValueModelMapper.selectAllOutputTime();
        return ApiResp.successWithObj(outputTimeList);
    }

    @Transactional
    @Override
    public ApiResp<Long> outputValueAdd(OutputAddEditReq req) {
        OutputValueModel existedOutputValueModel = this.outputValueModelMapper.selectByOutputTime(req.getOutputTime());
        if (existedOutputValueModel != null) {
            throw new BusinessException(ExceptionCodeEnum.OUTPUT_VALUE_EXISTED);
        }

        OutputValueModel outputValueModel = new OutputValueModel();
        outputValueModel.setCreateUserId(TokenHelper.getCurrentUser().getUserId());
        outputValueModel.setOutputTime(req.getOutputTime());
        this.outputValueModelMapper.insertSelective(outputValueModel);

        this.saveOutputValueDetailList(req.getOutputValueDetailList(), outputValueModel.getOutputValueId());
        return ApiResp.successWithObj(outputValueModel.getOutputValueId());
    }

    @Transactional
    @Override
    public ApiResp outputValueEdit(OutputAddEditReq req) {
        OutputValueModel existOutputValueModel = this.outputValueModelMapper.selectByOutputTime(req.getOutputTime());
        if (existOutputValueModel != null && !existOutputValueModel.getOutputValueId().equals(req.getOutputValueId())) {
            throw new BusinessException(ExceptionCodeEnum.OUTPUT_VALUE_EXISTED);
        }

        OutputValueModel outputValueModel = this.outputValueModelMapper.selectByPrimaryKey(req.getOutputValueId());
        if (outputValueModel == null) {
            throw new BusinessException(ExceptionCodeEnum.OUTPUT_VALUE_NOT_EXISTED);
        }

        outputValueModel.setOutputTime(req.getOutputTime());
        outputValueModel.setLastModifyTime(new Date());
        this.outputValueModelMapper.updateByPrimaryKey(outputValueModel);

        this.outputValueDetailModelMapper.deleteByOutputValueId(outputValueModel.getOutputValueId());
        this.saveOutputValueDetailList(req.getOutputValueDetailList(), outputValueModel.getOutputValueId());
        return ApiResp.success();
    }

    @Transactional
    @Override
    public ApiResp outputValueDelete(OutputValueIdReq req) {
        this.outputValueModelMapper.deleteByPrimaryKey(req.getOutputValueId());
        this.outputValueDetailModelMapper.deleteByOutputValueId(req.getOutputValueId());
        return ApiResp.success();
    }

    @Override
    public ApiResp getByOutputValueId(OutputValueIdReq req) {
        OutputValueModel outputValueModel = this.outputValueModelMapper.selectByPrimaryKey(req.getOutputValueId());
        if (outputValueModel == null) {
            throw new BusinessException(ExceptionCodeEnum.OUTPUT_VALUE_NOT_EXISTED);
        }

        List<OutputValueDetailModel> detailModelList = this.outputValueDetailModelMapper.selectListByOutputValueId(outputValueModel.getOutputValueId());

        List<OutputValueDetailVO> detailVOList = BeanUtils.convertList(detailModelList, OutputValueDetailVO.class);
        for (OutputValueDetailVO vo : detailVOList) {
            vo.setSubContractorName(this.subContractorModelMapper.selectByPrimaryKey(vo.getSubContractorId()).getSubContractorName());
        }

        OutputValueVO outputValueVO = BeanUtils.convert(outputValueModel, OutputValueVO.class);
        outputValueVO.setOutputValueDetailList(detailVOList);

        return ApiResp.successWithObj(outputValueVO);
    }

    /**
     * @description: 批量插入产值明细列表
     * @param:
     * @return:
     * @author: lubingbin
     * @date: 2019-08-06 23:15
     **/
    private void saveOutputValueDetailList(List<OutputValueDetailDTO> outputValueDetailDTOList, Long outputValueId) {
        if (CollectionUtils.isNotEmpty(outputValueDetailDTOList)) {
            for (OutputValueDetailDTO outputValueDetailDTO : outputValueDetailDTOList) {
                OutputValueDetailModel detailModel = new OutputValueDetailModel();
                detailModel.setOutputCategoryAmount(outputValueDetailDTO.getOutputCategoryAmount());
                detailModel.setOutputStepAmount(outputValueDetailDTO.getOutputStepAmount());
                detailModel.setOutputAmount(outputValueDetailDTO.getOutputCategoryAmount().add(outputValueDetailDTO.getOutputStepAmount()));
                detailModel.setIncomeAmount(detailModel.getOutputAmount().multiply(new BigDecimal("0.85")));
                detailModel.setIncomeStepAmount(outputValueDetailDTO.getOutputStepAmount().multiply(new BigDecimal("0.85")));
                detailModel.setIncomeCategoryAmount(outputValueDetailDTO.getOutputCategoryAmount().multiply(new BigDecimal("0.85")));
                detailModel.setPayAmount(detailModel.getIncomeAmount().multiply(new BigDecimal("0.85")));
                detailModel.setCreateUserId(TokenHelper.getCurrentUser().getUserId());
                detailModel.setOutputValueId(outputValueId);
                detailModel.setSubContractorId(outputValueDetailDTO.getSubContractorId());
                this.outputValueDetailModelMapper.insertSelective(detailModel);
            }
        }
    }
}
