package com.lbb.bean.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OutputValueDetailVO {

    private Long outputValueDetailId;

    private Integer subContractorId;

    private String subContractorName;

    private Long outputValueId;

    private BigDecimal outputAmount;

    private BigDecimal outputCategoryAmount;

    private BigDecimal outputStepAmount;

    private BigDecimal incomeAmount;

    private BigDecimal incomeCategoryAmount;

    private BigDecimal incomeStepAmount;

    private BigDecimal payAmount;

    private Integer createUserId;

    private Date createTime;

    private Date lastModifyTime;

}
