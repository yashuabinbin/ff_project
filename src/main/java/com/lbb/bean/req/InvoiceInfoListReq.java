package com.lbb.bean.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class InvoiceInfoListReq extends PageReq implements Serializable {

    private static final long serialVersionUID = 8547540746007836147L;

    @JsonProperty("contractNum")
    private String contractNum;

    @JsonProperty("subContractorId")
    private Integer subContractorId;

    @JsonProperty("invoiceStartTime")
    private Date invoiceStartTime;

    @JsonProperty("invoiceEndTime")
    private Date invoiceEndTime;
}
