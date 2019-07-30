package com.lbb.dao;

import com.lbb.model.OutputValueDetailModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OutputValueDetailModelMapper {

    int deleteByPrimaryKey(Long relationId);

    int insert(OutputValueDetailModel record);

    int insertSelective(OutputValueDetailModel record);

    OutputValueDetailModel selectByPrimaryKey(Long relationId);

    int updateByPrimaryKeySelective(OutputValueDetailModel record);

    int updateByPrimaryKey(OutputValueDetailModel record);

    void deleteByOutputId(@Param("outputValueId") Long outputValueId);

    List<OutputValueDetailModel> selectListByOutputValueId(@Param("outputValueId") Long outputValueId);
}