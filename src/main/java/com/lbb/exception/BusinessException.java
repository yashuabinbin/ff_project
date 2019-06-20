package com.lbb.exception;

import com.lbb.enums.ExceptionCodeEnum;
import lombok.Data;

/**
 * @description: 业务异常
 * @param:
 * @return:
 * @author: lubingbin
 * @date: 2019/5/30 5:08 PM
 **/
@Data
public class BusinessException extends RuntimeException {

    private String errorCode;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public BusinessException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getMsg());
        this.errorCode = exceptionCodeEnum.getCode();
    }

    public BusinessException(ExceptionCodeEnum exceptionCodeEnum, String msg) {
        super(msg);
        this.errorCode = exceptionCodeEnum.getCode();
    }
}
