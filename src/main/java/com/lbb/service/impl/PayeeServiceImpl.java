package com.lbb.service.impl;

import com.lbb.dao.PayeeModelMapper;
import com.lbb.model.PayeeModel;
import com.lbb.service.PayeeService;
import com.lbb.utils.TokenHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PayeeServiceImpl implements PayeeService {

    @Resource
    private PayeeModelMapper payeeModelMapper;

    @Override
    public Integer saveNotExist(String payeeName) {
        PayeeModel payeeModel = payeeModelMapper.selectByPayeeName(payeeName);
        if (payeeModel == null) {
            payeeModel = new PayeeModel(payeeName);
            payeeModel.setCreateUserId(TokenHelper.getCurrentUser().getUserId());
            payeeModelMapper.insertSelective(payeeModel);
        }
        return payeeModel.getPayeeId();
    }

    @Override
    public List<PayeeModel> selectListByPayeeName(String payeeName) {
        return payeeModelMapper.selectListByPayeeName(payeeName);
    }
}
