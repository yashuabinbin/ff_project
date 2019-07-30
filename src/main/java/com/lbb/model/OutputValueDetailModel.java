package com.lbb.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OutputValueDetailModel extends BaseModel {

    private Long relationId;

    private Integer subContractorId;

    private Long outputValueId;

    private BigDecimal outputValueRate;

    private BigDecimal subContractorAmount;

    private BigDecimal migrantWorkerAmount;

    private Integer createUserId;

    private Date createTime;

    private Date lastModifyTime;

}