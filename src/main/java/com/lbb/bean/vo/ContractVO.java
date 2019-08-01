package com.lbb.bean.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@ToString
public class ContractVO implements Serializable {

    @ApiModelProperty(value = "合同id")
    private Integer contractId;

    @ApiModelProperty(value = "合同号")
    private String contractNum;

    @ApiModelProperty(value = "合同名称")
    private String contractName;

    @ApiModelProperty(value = "合同描述")
    private String contractDesc;

    @ApiModelProperty(value = "对方单位")
    private String companyName;

    @ApiModelProperty(value = "合同金额")
    private BigDecimal contractAmount;

    @ApiModelProperty(value = "税率")
    private BigDecimal taxRate;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    private List<SubContractorWithShareRateVO> subContractorList;

}
