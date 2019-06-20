package com.lbb.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ContractSubContractorRelationModel extends BaseModel {

    private Integer relationId;

    private Integer contractId;

    private Integer subContractorId;

    private BigDecimal shareRate;

}