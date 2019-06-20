package com.lbb.dao;

import com.lbb.model.SysUserModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserModelMapper {

    int deleteByPrimaryKey(Integer userId);

    int insert(SysUserModel record);

    int insertSelective(SysUserModel record);

    SysUserModel selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(SysUserModel record);

    int updateByPrimaryKey(SysUserModel record);

    /**
     * @description: 根据用户名获取对象
     * @param:
     * @return:
     * @author: lubingbin
     * @date: 2019/5/30 5:03 PM
     **/
    SysUserModel selectByUserName(@Param("username") String username);
}