package com.lbb.bean.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class SubContractorEditAddReq implements Serializable {

    private static final long serialVersionUID = -1899160591527525945L;

    @JsonProperty("subContractorId")
    private Integer subContractorId;

    @JsonProperty("subContractorName")
    private String subContractorName;

}
