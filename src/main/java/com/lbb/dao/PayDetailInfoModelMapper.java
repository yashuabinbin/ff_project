package com.lbb.dao;

import com.lbb.model.PayDetailInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PayDetailInfoModelMapper {

    int deleteByPrimaryKey(Long payDetailId);

    int insert(PayDetailInfoModel record);

    int insertSelective(PayDetailInfoModel record);

    PayDetailInfoModel selectByPrimaryKey(Long payDetailId);

    int updateByPrimaryKeySelective(PayDetailInfoModel record);

    int updateByPrimaryKey(PayDetailInfoModel record);

    List<Integer> selectContractIdListBySubContractorId(@Param("subContractorId") Integer subContractorId);

    List<PayDetailInfoModel> selectListByPayId(@Param("payId") Long payId);

    void deleteByPayId(@Param("payId") Long payId);

    List<PayDetailInfoModel> selectListBySubContractorId(@Param("subContractorId") Integer subContractorId);

    List<PayDetailInfoModel> selectListByPayIds(@Param("payIdList") List<Long> payIdList);
}