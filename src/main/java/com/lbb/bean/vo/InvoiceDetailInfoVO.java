package com.lbb.bean.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
public class InvoiceDetailInfoVO implements Serializable {

    private static final long serialVersionUID = 256522107722092134L;

    private Integer subContractorId;

    private String subContractorName;

    private BigDecimal subContractorDeductAmount;

}
