package com.lbb.bean.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OutputValueVO implements Serializable {

    private Long outputValueId;

    private BigDecimal subContractorAmount;

    private BigDecimal migrantWorkAmount;

    private Date outputTime;

    private String remark;

    private List<OutputValueDetailVO> outputValueDetailList;
}
