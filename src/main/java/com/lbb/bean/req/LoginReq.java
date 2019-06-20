package com.lbb.bean.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@ApiModel
@Data
@ToString
public class LoginReq {

    @ApiModelProperty(value = "用户名", dataType = "string", required = true)
    private String username;

    @ApiModelProperty(value = "密码", dataType = "string", required = true)
    private String password;

    @ApiModelProperty(value = "验证码", dataType = "string", required = true)
    private String validCode;

}
