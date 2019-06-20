package com.lbb.service.impl;


import com.lbb.bean.Const;
import com.lbb.bean.req.LoginReq;
import com.lbb.bean.resp.ApiResp;
import com.lbb.bean.resp.LoginResp;
import com.lbb.bean.vo.SysUserVO;
import com.lbb.dao.SysUserModelMapper;
import com.lbb.enums.ExceptionCodeEnum;
import com.lbb.exception.BusinessException;
import com.lbb.model.SysUserModel;
import com.lbb.service.UserService;
import com.lbb.utils.BeanUtils;
import com.lbb.utils.Md5Utils;
import com.lbb.utils.RedisUtils;
import com.lbb.utils.TokenHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private SysUserModelMapper sysUserModelMapper;

    @Resource
    private RedisUtils redisUtils;

    @Override
    public ApiResp<LoginResp> login(LoginReq dto) {
        log.info("执行 {} 方法，参数:{}", "login", dto.toString());
        SysUserModel userModel = sysUserModelMapper.selectByUserName(dto.getUsername());
        if (userModel == null) {
            throw new BusinessException(ExceptionCodeEnum.LOGIN_FAIL);
        }

        String md5Password = Md5Utils.md5(dto.getPassword(), userModel.getSalt());
        if (userModel.getPassword().equals(md5Password)) {
            throw new BusinessException(ExceptionCodeEnum.LOGIN_FAIL);
        }

        // 登录成功 更新user
        userModel.setLastLoginTime(new Date());
        sysUserModelMapper.updateByPrimaryKeySelective(userModel);

        LoginResp loginResp = new LoginResp();
        loginResp.setToken(UUID.randomUUID().toString().replace("-", ""));
        loginResp.setSysUser(BeanUtils.convert(userModel, SysUserVO.class));

        redisUtils.set(Const.TOKEN_PRE + userModel.getUserId(), loginResp.getToken(), 1000L);
        redisUtils.set(loginResp.getToken(), loginResp.getSysUser(), 1000L);

        return ApiResp.successWithObj(loginResp);
    }

    @Override
    public ApiResp logout() {
        Integer userId = TokenHelper.getCurrentUser().getUserId();
        String token = TokenHelper.getToken();
        if (userId != null) {
            redisUtils.del(Const.TOKEN_PRE + userId);
            redisUtils.del(token);
        }
        return ApiResp.success();
    }
}
