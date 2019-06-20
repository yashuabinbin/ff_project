package com.lbb.dao;

import com.lbb.model.ContractSubContractorRelationModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ContractSubContractorRelationModelMapper {

    int deleteByPrimaryKey(Integer relationId);

    int insert(ContractSubContractorRelationModel record);

    int insertSelective(ContractSubContractorRelationModel record);

    ContractSubContractorRelationModel selectByPrimaryKey(Integer relationId);

    int updateByPrimaryKeySelective(ContractSubContractorRelationModel record);

    int updateByPrimaryKey(ContractSubContractorRelationModel record);

    List<ContractSubContractorRelationModel> selectListByContractId(@Param("contractId") Integer contractId);

    void deleteByContractId(@Param("contractId") Integer contractId);

    void deleteBySubContractorId(@Param("subContractorId") Integer subContractorId);
}