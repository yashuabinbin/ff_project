package com.lbb.bean.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OutputValueIdReq {

    @JsonProperty(value = "outputValueId")
    private Long outputValueId;

}
