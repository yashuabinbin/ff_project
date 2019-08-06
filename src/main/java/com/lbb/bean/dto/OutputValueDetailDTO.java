package com.lbb.bean.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OutputValueDetailDTO {

    @JsonProperty(value = "subContractorId")
    private Integer subContractorId;

    @JsonProperty(value = "outputStepAmount")
    private BigDecimal outputStepAmount;

    @JsonProperty(value = "outputCategoryAmount")
    private BigDecimal outputCategoryAmount;

}
