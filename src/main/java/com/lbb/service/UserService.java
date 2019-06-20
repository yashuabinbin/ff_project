package com.lbb.service;

import com.lbb.bean.req.LoginReq;
import com.lbb.bean.resp.ApiResp;
import com.lbb.bean.resp.LoginResp;

public interface UserService {

    /**
     * @description: 登录校验
     * @param:
     * @return:
     * @author: lubingbin
     * @date: 2019/5/30 6:12 PM
     **/
    ApiResp<LoginResp> login(LoginReq dto);

    /**
     * @description: 退出登录
     * @param:
     * @return:
     * @author: lubingbin
     * @date: 2019/6/1 8:02 PM
     **/
    ApiResp logout();
}
