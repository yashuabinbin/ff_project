package com.lbb.dao;

import com.lbb.model.ShareRateModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShareRateModelMapper {

    int deleteByPrimaryKey(Integer shareId);

    int insert(ShareRateModel record);

    int insertSelective(ShareRateModel record);

    ShareRateModel selectByPrimaryKey(Integer shareId);

    int updateByPrimaryKeySelective(ShareRateModel record);

    int updateByPrimaryKey(ShareRateModel record);
}