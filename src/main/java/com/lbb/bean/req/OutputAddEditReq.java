package com.lbb.bean.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lbb.bean.dto.OutputValueDetailDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OutputAddEditReq {

    @JsonProperty(value = "outputValueId")
    private Long outputValueId;

    @JsonProperty(value = "outputTime")
    private Integer outputTime;

    @JsonProperty(value = "outputValueDetailList")
    List<OutputValueDetailDTO> outputValueDetailList;

}
