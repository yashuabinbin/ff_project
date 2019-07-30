package com.lbb.bean.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class OutputValueIdReq implements Serializable {

    private static final long serialVersionUID = 1879674809998184764L;

    @JsonProperty(value = "outputValueId")
    private Long outputValueId;

}
