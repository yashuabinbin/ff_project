package com.lbb.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InvoiceDetailInfoModel extends BaseModel {

    private Long invoiceDetailId;

    private Long invoiceId;

    private Integer subContractorId;

    private BigDecimal shareRate;

    private Integer contractId;

    private BigDecimal shareAmount;

    private Integer createUserId;

}