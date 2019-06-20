package com.lbb.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class InvoiceInfoModel extends BaseModel {
    
    private Long invoiceId;

    private Integer contractId;

    private String invoiceContent;

    private Date invoiceTime;

    private BigDecimal beforeTaxAmount;

    private BigDecimal taxRate;

    private BigDecimal deductAmount;

    private String remark;

}