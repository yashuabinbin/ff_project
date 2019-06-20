package com.lbb.bean.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class SubContractorIdReq implements Serializable {

    @JsonProperty(value = "subContractorId")
    private Integer subContractorId;

}
