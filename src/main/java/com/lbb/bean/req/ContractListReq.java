package com.lbb.bean.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ContractListReq extends PageReq {

    private static final long serialVersionUID = 6469546397358797979L;

    @ApiModelProperty(value = "合同人名")
    @JsonProperty("contractName")
    private String contractName;

    @ApiModelProperty(value = "合同编号")
    @JsonProperty("contractNum")
    private String contractNum;

}
