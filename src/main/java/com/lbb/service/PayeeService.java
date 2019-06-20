package com.lbb.service;

import com.lbb.model.PayeeModel;

import java.util.List;

public interface PayeeService {

    Integer saveNotExist(String payeeName);

    List<PayeeModel> selectListByPayeeName(String payeeName);

}
