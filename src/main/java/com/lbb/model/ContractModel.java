package com.lbb.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ContractModel extends BaseModel {

    private Integer contractId;

    private String contractNum;

    private String contractName;

    private String contractDesc;

    private BigDecimal taxRate;

}