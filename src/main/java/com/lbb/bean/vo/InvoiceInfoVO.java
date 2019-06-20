package com.lbb.bean.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@ToString
public class InvoiceInfoVO implements Serializable {

    private Long invoiceId;

    private String invoiceContent;

    private Date invoiceTime;

    private BigDecimal beforeTaxAmount;

    private BigDecimal taxRate;

    private BigDecimal deductAmount;

    private String remark;

    private Integer contractId;

    private String contractNum;

    private String contractName;

    private List<InvoiceDetailInfoVO> invoiceDetailInfoList;
}
