package com.lbb.handler;

import com.lbb.bean.resp.ApiResp;
import com.lbb.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<Object> businessExceptionHandler(BusinessException e) {
        log.error("GlobalExceptionHandler, errorcode={}, message={}", e.getErrorCode(), e.getMessage());

        ApiResp apiResp = new ApiResp(e.getErrorCode(), e.getMessage());
        return new ResponseEntity<>(apiResp, HttpStatus.OK);
    }


}
