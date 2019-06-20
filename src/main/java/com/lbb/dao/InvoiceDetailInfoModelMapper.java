package com.lbb.dao;

import com.lbb.model.InvoiceDetailInfoModel;
import com.lbb.model.PayDetailInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InvoiceDetailInfoModelMapper {

    int deleteByPrimaryKey(Long invoiceDetailId);

    int insert(InvoiceDetailInfoModel record);

    int insertSelective(InvoiceDetailInfoModel record);

    InvoiceDetailInfoModel selectByPrimaryKey(Long invoiceDetailId);

    int updateByPrimaryKeySelective(InvoiceDetailInfoModel record);

    int updateByPrimaryKey(InvoiceDetailInfoModel record);

    void deleteByInvoiceId(@Param("invoiceId") Long invoiceId);

    List<Integer> selectContractIdListBySubContractorId(@Param("subContractorId") Integer subContractorId);

    List<InvoiceDetailInfoModel> selectListByInvoiceId(@Param("invoiceId") Long invoiceId);

    List<InvoiceDetailInfoModel> selectListBySubContractorId(@Param("subContractorId") Integer subContractorId);
}