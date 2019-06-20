package com.lbb.bean.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class ContractIdReq implements Serializable {

    private static final long serialVersionUID = 1543255763048899634L;

    @JsonProperty(value = "contractId")
    private Integer contractId;

}
