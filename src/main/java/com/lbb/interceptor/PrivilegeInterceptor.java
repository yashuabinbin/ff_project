package com.lbb.interceptor;

import com.lbb.model.SysUserModel;
import com.lbb.utils.TokenHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class PrivilegeInterceptor implements HandlerInterceptor {

    @Resource
    private TokenHelper tokenHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");

        log.info("token: {}", token);
        log.info("ip: {}", request.getRemoteAddr());
        log.info("url: {}", request.getRequestURI());
        tokenHelper.tokenValidate(token);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        TokenHelper.removeCurrentUser();
    }
}
