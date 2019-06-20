package com.lbb.bean.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class PageReq implements Serializable {

    @JsonProperty("pageIndex")
    private Integer pageIndex;

    @JsonProperty("pageSize")
    private Integer pageSize;

}
