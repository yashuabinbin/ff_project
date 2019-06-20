package com.lbb.controller;

import com.lbb.bean.req.LoginReq;
import com.lbb.bean.resp.ApiResp;
import com.lbb.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/")
public class LoginController extends BaseController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "登录")
    @ApiImplicitParam(value = "登录", dataType = "LoginReq")
    @PostMapping(value = "/login")
    public ApiResp login(@RequestBody LoginReq dto) {
        return userService.login(dto);
    }


    @PostMapping(value = "/logout")
    public ApiResp login() {
        return userService.logout();
    }
}
