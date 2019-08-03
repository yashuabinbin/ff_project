package com.lbb.bean.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@ToString
@Data
public class SubContractorDTO implements Serializable {

    private static final long serialVersionUID = -6651981524325192155L;

    @JsonProperty("subContractorId")
    private Integer subContractorId;

    @JsonProperty("subContractorAmount")
    private BigDecimal shareRate;

}
