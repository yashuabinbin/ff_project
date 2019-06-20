package com.lbb.dao;

import com.lbb.model.InvoiceInfoModel;
import com.lbb.model.PayInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface InvoiceInfoModelMapper {

    int deleteByPrimaryKey(Long invoiceId);

    int insert(InvoiceInfoModel record);

    int insertSelective(InvoiceInfoModel record);

    InvoiceInfoModel selectByPrimaryKey(Long invoiceId);

    int updateByPrimaryKeySelective(InvoiceInfoModel record);

    int updateByPrimaryKey(InvoiceInfoModel record);

    List<String> selectInvoiceContent();

    List<InvoiceInfoModel> selectListByParams(@Param("contractIdList") List<Integer> searchContractIdList,
                                              @Param("invoiceStartTime") Date invoiceStartTime, @Param("invoiceEndTime") Date invoiceEndTime);

    List<InvoiceInfoModel> selectListByContractId(@Param("contractId") Integer contractId);
}