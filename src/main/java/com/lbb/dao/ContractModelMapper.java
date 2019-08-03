package com.lbb.dao;

import com.lbb.model.ContractModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface ContractModelMapper {

    int deleteByPrimaryKey(Integer contractId);

    int insert(ContractModel record);

    int insertSelective(ContractModel record);

    ContractModel selectByPrimaryKey(Integer contractId);

    int updateByPrimaryKeySelective(ContractModel record);

    int updateByPrimaryKey(ContractModel record);

    List<ContractModel> selectListByNameAndNum(@Param("contractName") String contractName, @Param("contractNum") String contractNum);

    List<ContractModel> selectAll();

    List<Integer> selectContractIdListByContractNum(@Param("contractNum") String contractNum);

    List<ContractModel> selectListByContractNum(@Param("contractNum") String contractNum);

    List<ContractModel> selectListByContractName(@Param("contractName")String contractName);

    List<Integer> selectContractorIdListByParams(@Param("contractName") String contractName, @Param("contractNum") String contractNum);

    BigDecimal selectContractAmountSum(@Param("contractIdList") List<Integer> contractIdList);
}
