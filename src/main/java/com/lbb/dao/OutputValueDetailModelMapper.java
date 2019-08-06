package com.lbb.dao;

import com.lbb.model.OutputValueDetailModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface OutputValueDetailModelMapper {
    int deleteByPrimaryKey(Long outputValueDetailId);

    int insert(OutputValueDetailModel record);

    int insertSelective(OutputValueDetailModel record);

    OutputValueDetailModel selectByPrimaryKey(Long outputValueDetailId);

    int updateByPrimaryKeySelective(OutputValueDetailModel record);

    int updateByPrimaryKey(OutputValueDetailModel record);

    List<OutputValueDetailModel> selectListByOutputValueId(@Param("outputValueId") Long outputValueId);

    void deleteByOutputValueId(@Param("outputValueId") Long outputValueId);
}