package com.lbb.bean.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OutputValueDetailVO implements Serializable {

    private static final long serialVersionUID = 4540042939935377185L;

    private Integer subContractorId;

    private String subContractorName;

    private BigDecimal subContractorAmount;

    private BigDecimal migrantWorkerAmount;

}
