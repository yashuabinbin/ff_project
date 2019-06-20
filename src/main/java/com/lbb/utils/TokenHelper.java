package com.lbb.utils;

import com.alibaba.fastjson.JSONObject;
import com.lbb.bean.Const;
import com.lbb.enums.ExceptionCodeEnum;
import com.lbb.exception.BusinessException;
import com.lbb.model.SysUserModel;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TokenHelper {

    private static ThreadLocal<SysUserModel> USER_THREADLOCAL = new ThreadLocal<>();
    private static ThreadLocal<String> TOKEN_THREADLOCAL = new ThreadLocal<>();

    @Resource
    private RedisUtils redisUtils;

    public void tokenValidate(String token) {
        String userJson = redisUtils.get(token);
        SysUserModel sysUserModel = JSONObject.parseObject(userJson, SysUserModel.class);
        if (sysUserModel == null) {
            throw new BusinessException(ExceptionCodeEnum.UNLOGIN);
        }

        // 延时
        redisUtils.set(Const.TOKEN_PRE + sysUserModel.getUserId(), token, 10L);
        redisUtils.set(token, JSONObject.toJSONString(sysUserModel), 15L);

        USER_THREADLOCAL.set(sysUserModel);
        TOKEN_THREADLOCAL.set(token);
    }

    public static SysUserModel getCurrentUser() {
        return USER_THREADLOCAL.get();
    }

    public static String getToken() {
        return TOKEN_THREADLOCAL.get();
    }

    public static void removeCurrentUser() {
        USER_THREADLOCAL.remove();
        TOKEN_THREADLOCAL.remove();
    }

}
