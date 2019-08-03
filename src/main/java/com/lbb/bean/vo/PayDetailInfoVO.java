package com.lbb.bean.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PayDetailInfoVO {

    private Integer subContractorId;

    private String subContractorName;

    private BigDecimal subContractorAmount;

}
