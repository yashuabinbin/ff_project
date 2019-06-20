package com.lbb.bean.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class InvoiceIdReq {

    @JsonProperty("invoiceId")
    private Long invoiceId;

}
