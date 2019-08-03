package com.lbb.bean.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
public class InvoiceDetailInfoDTO implements Serializable {

    @JsonProperty("subContractorId")
    private Integer subContractorId;

    @JsonProperty("subContractorDeductAmount")
    private BigDecimal subContractorDeductAmount;

}
