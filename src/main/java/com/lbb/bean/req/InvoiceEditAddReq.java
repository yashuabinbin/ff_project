package com.lbb.bean.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lbb.bean.dto.InvoiceDetailInfoDTO;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@ToString
public class InvoiceEditAddReq implements Serializable {

    private static final long serialVersionUID = -8202583732405836206L;

    @JsonProperty("invoiceId")
    private Long invoiceId;

    @JsonProperty("contractId")
    private Integer contractId;

    @JsonProperty("invoiceContent")
    private String invoiceContent;

    @JsonProperty("invoiceTime")
    private Date invoiceTime;

    @JsonProperty("beforeTaxAmount")
    private BigDecimal beforeTaxAmount;

    @JsonProperty("taxRate")
    private BigDecimal taxRate;

    @JsonProperty("deductAmount")
    private BigDecimal deductAmount;

    @JsonProperty("remark")
    private String remark;

    @JsonProperty("invoiceDetailInfoList")
    private List<InvoiceDetailInfoDTO> invoiceDetailInfoList;
}
