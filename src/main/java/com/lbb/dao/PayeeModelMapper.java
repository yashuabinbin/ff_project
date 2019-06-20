package com.lbb.dao;

import com.lbb.model.PayeeModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PayeeModelMapper {

    int deleteByPrimaryKey(Integer payeeId);

    int insert(PayeeModel record);

    int insertSelective(PayeeModel record);

    PayeeModel selectByPrimaryKey(Integer payeeId);

    int updateByPrimaryKeySelective(PayeeModel record);

    int updateByPrimaryKey(PayeeModel record);

    PayeeModel selectByPayeeName(@Param("payeeName") String payeeName);

    List<PayeeModel> selectListByPayeeName(@Param("payeeName") String payeeName);

    List<PayeeModel> selectListByPayeeIdList(@Param("payeeIdList") List<Integer> payeeIdList);
}