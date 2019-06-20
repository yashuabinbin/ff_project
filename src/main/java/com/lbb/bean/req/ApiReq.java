package com.lbb.bean.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class ApiReq {

    @ApiModelProperty(value = "token", hidden = true)
    private String token;

}

