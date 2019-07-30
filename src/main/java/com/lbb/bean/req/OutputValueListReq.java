package com.lbb.bean.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OutputValueListReq extends PageReq implements Serializable {

    private static final long serialVersionUID = -6592270026919173405L;

    @JsonProperty("outputValueStartTime")
    private Date outputValueStartTime;

    @JsonProperty("outputValueEndTime")
    private Date outputValueEndTime;

}
