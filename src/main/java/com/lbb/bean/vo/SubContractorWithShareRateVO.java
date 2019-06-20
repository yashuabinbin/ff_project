package com.lbb.bean.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class SubContractorWithShareRateVO implements Serializable {

    private static final long serialVersionUID = 6910419000812535896L;

    private Integer subContractorId;

    private String subContractorName;

    private BigDecimal shareRate;

}
