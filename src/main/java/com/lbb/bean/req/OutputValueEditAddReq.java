package com.lbb.bean.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lbb.bean.dto.OutputValueDetailDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OutputValueEditAddReq {

    @JsonProperty("outputValueId")
    private Long outputValueId;

    @JsonProperty("outputValueAmount")
    private BigDecimal outputValueAmount;

    @JsonProperty("migrantWorkerAmount")
    private BigDecimal migrantWorkerAmount;

    @JsonProperty("remark")
    private String remark;

    @JsonProperty("outputTime")
    private Date outputTime;

    @JsonProperty("outputValueDetailList")
    private List<OutputValueDetailDTO> outputValueDetailList;
}
