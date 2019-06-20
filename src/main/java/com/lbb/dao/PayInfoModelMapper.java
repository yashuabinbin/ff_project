package com.lbb.dao;

import com.lbb.model.PayInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface PayInfoModelMapper {

    int deleteByPrimaryKey(Long payId);

    int insert(PayInfoModel record);

    int insertSelective(PayInfoModel record);

    PayInfoModel selectByPrimaryKey(Long payId);

    int updateByPrimaryKeySelective(PayInfoModel record);

    int updateByPrimaryKey(PayInfoModel record);

    List<PayInfoModel> selectListByParams(@Param("contractIdList") List<Integer> contractIdList,
                                          @Param("payee") String payee, @Param("payType") String payType,
                                          @Param("payStartTime") Date payStartTime, @Param("payEndTime") Date payEndTime);

    List<String> selectPayeeList();

    List<PayInfoModel> selectListByContractId(@Param("contractId") Integer contractId);
}