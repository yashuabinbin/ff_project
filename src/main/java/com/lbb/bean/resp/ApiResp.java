package com.lbb.bean.resp;

import com.lbb.enums.ResultCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class ApiResp<T> {

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "信息")
    private String msg;

    @ApiModelProperty(value = "数据")
    private T result;

    public ApiResp() {
    }

    public ApiResp(String status) {
        this.status = status;
    }

    public ApiResp(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ApiResp(String status, T result) {
        this.status = status;
        this.result = result;
    }

    public ApiResp(String status, String msg, T result) {
        this.status = status;
        this.msg = msg;
        this.result = result;
    }

    public static ApiResp success() {
        return new ApiResp(ResultCodeEnum.SUCCESS.getCode());
    }

    public static ApiResp successWithMsg(String msg) {
        return new ApiResp(ResultCodeEnum.SUCCESS.getCode(), msg);
    }

    public static <T> ApiResp successWithObj(T t) {
        return new ApiResp(ResultCodeEnum.SUCCESS.getCode(), t);
    }


    public static ApiResp error() {
        return new ApiResp(ResultCodeEnum.ERROR.getCode());
    }

    public static ApiResp errorWithMsg(String msg) {
        return new ApiResp(ResultCodeEnum.ERROR.getCode(), msg);
    }

    public static <T> ApiResp errorWithObj(T t) {
        return new ApiResp(ResultCodeEnum.ERROR.getCode(), t);
    }

}
