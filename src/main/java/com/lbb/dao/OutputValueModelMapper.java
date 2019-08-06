package com.lbb.dao;

import com.lbb.model.OutputValueModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface OutputValueModelMapper {

    int deleteByPrimaryKey(Long outputValueId);

    int insert(OutputValueModel record);

    int insertSelective(OutputValueModel record);

    OutputValueModel selectByPrimaryKey(Long outputValueId);

    int updateByPrimaryKeySelective(OutputValueModel record);

    int updateByPrimaryKey(OutputValueModel record);

    OutputValueModel selectByOutputTime(@Param("outputTime") Integer outputTime);

    List<Integer> selectAllOutputTime();
}