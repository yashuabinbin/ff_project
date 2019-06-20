package com.lbb.bean.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SubContractorListReq extends PageReq {

    private static final long serialVersionUID = 6469546397358797979L;

    @ApiModelProperty(value = "分包名称")
    @JsonProperty("subContractorName")
    private String subContractorName;

}
