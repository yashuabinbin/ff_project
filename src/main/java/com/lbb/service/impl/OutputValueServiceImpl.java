package com.lbb.service.impl;

import com.lbb.bean.dto.OutputValueDetailDTO;
import com.lbb.bean.req.OutputAddEditReq;
import com.lbb.bean.req.OutputSearchReq;
import com.lbb.bean.req.OutputValueIdReq;
import com.lbb.bean.resp.ApiResp;
import com.lbb.bean.vo.OutputValueDetailVO;
import com.lbb.bean.vo.OutputValueVO;
import com.lbb.dao.OutputValueDetailModelMapper;
import com.lbb.dao.OutputValueModelMapper;
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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

@Service
public class OutputValueServiceImpl implements OutputValueService {

    @Resource
    private OutputValueModelMapper outputValueModelMapper;

    @Resource
    private OutputValueDetailModelMapper outputValueDetailModelMapper;

    @Override
    public ApiResp<OutputValueVO> getByOutputTime(OutputSearchReq req) {
        OutputValueVO outputValueVO = null;

        OutputValueModel outputValueModel = this.outputValueModelMapper.selectByOutputTime(req.getOutputTime());
        if (outputValueModel == null) {
            return ApiResp.success();
        }

        List<OutputValueDetailModel> outputValueDetailModelList = this.outputValueDetailModelMapper.selectListByOutputValueId(outputValueModel.getOutputValueId());
        outputValueVO = BeanUtils.convert(outputValueModel, OutputValueVO.class);
        outputValueVO.setOutputValueDetailVOList(BeanUtils.convertList(outputValueDetailModelList, OutputValueDetailVO.class));
        return ApiResp.successWithObj(outputValueVO);
    }

    @Override
    public ApiResp<List<Integer>> getOutputTimeList() {
        List<Integer> outputTimeList = this.outputValueModelMapper.selectAllOutputTime();
        return ApiResp.errorWithObj(outputTimeList);
    }

    @Transactional
    @Override
    public ApiResp<Long> outputValueAdd(OutputAddEditReq req) {
        OutputValueModel existedOutputValueModel = this.outputValueModelMapper.selectByOutputTime(req.getOutputTime());
        if (existedOutputValueModel != null) {
            throw new BusinessException(ExceptionCodeEnum.OUTPUT_VALUE_EXISTED);
        }

        OutputValueModel outputValueModel = new OutputValueModel();
        outputValueModel.setCreateUserId(TokenHelper.getCurrentUser().getCreateUserId());
        outputValueModel.setOutputTime(req.getOutputTime());
        this.outputValueModelMapper.insertSelective(outputValueModel);

        this.saveOutputValueDetailList(req.getOutputValueDetailList(), outputValueModel.getOutputValueId());
        return ApiResp.successWithObj(outputValueModel.getOutputValueId());
    }

    @Transactional
    @Override
    public ApiResp outputValueEdit(OutputAddEditReq req) {
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
                detailModel.setCreateUserId(TokenHelper.getCurrentUser().getCreateUserId());
                detailModel.setOutputValueId(outputValueId);
                detailModel.setSubContractorId(outputValueDetailDTO.getSubContractorId());
                this.outputValueDetailModelMapper.insertSelective(detailModel);
            }
        }
    }
}
