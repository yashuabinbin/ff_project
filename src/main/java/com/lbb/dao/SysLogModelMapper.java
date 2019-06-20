package com.lbb.dao;

import com.lbb.model.SysLogModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysLogModelMapper {

    int deleteByPrimaryKey(Long logId);

    int insert(SysLogModel record);

    int insertSelective(SysLogModel record);

    SysLogModel selectByPrimaryKey(Long logId);

    int updateByPrimaryKeySelective(SysLogModel record);

    int updateByPrimaryKey(SysLogModel record);
}