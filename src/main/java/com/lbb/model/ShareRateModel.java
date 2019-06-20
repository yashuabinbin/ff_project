package com.lbb.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ShareRateModel extends BaseModel {

    private Integer shareId;

    private Integer contractId;

    private Integer subContractorId;

    private BigDecimal rate;

    private String status;

    private Date deleteTime;

    private Integer deleteUserId;

}