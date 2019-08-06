package com.lbb.bean.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OutputSearchReq {

    @JsonProperty(value = "outputTime")
    private Integer outputTime;

}
