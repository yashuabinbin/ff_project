package com.lbb.dao;

import com.lbb.model.OutputValueModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface OutputValueModelMapper {

    int deleteByPrimaryKey(Long outputValueId);

    int insert(OutputValueModel record);

    int insertSelective(OutputValueModel record);

    OutputValueModel selectByPrimaryKey(Long outputValueId);

    int updateByPrimaryKeySelective(OutputValueModel record);

    int updateByPrimaryKey(OutputValueModel record);

    List<OutputValueModel> selectByParams(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
}