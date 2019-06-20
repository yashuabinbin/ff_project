package com.lbb.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PayInfoModel extends BaseModel {

    private Long payId;

    private Integer contractId;

    private String payee;

    private Date payTime;

    private BigDecimal payAmount;

    private BigDecimal performanceBoundAmount;

    private String payType;

    private String remark;

}