package com.lbb.dao;

import com.lbb.model.SubContractorModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SubContractorModelMapper {

    int deleteByPrimaryKey(Integer subContractorId);

    int insert(SubContractorModel record);

    int insertSelective(SubContractorModel record);

    SubContractorModel selectByPrimaryKey(Integer subContractorId);

    int updateByPrimaryKeySelective(SubContractorModel record);

    int updateByPrimaryKey(SubContractorModel record);

    List<SubContractorModel> selectListByName(@Param("subContractorName") String subContractorName);

    List<SubContractorModel> selectAll();
}