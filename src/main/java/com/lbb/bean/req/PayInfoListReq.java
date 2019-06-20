package com.lbb.bean.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class PayInfoListReq extends PageReq implements Serializable {

    private static final long serialVersionUID = 2555330112691470117L;
    
    @JsonProperty("contractNum")
    private String contractNum;

    @JsonProperty("payee")
    private String payee;

    @JsonProperty("subContractorId")
    private Integer subContractorId;

    @JsonProperty("payStartTime")
    private Date payStartTime;

    @JsonProperty("payEndTime")
    private Date payEndTime;

    @JsonProperty("payType")
    private String payType;

}
