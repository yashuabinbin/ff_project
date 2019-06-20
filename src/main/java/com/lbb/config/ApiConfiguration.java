package com.lbb.config;

import com.lbb.interceptor.PrivilegeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;
import java.util.List;

@Configuration
public class ApiConfiguration extends WebMvcConfigurationSupport {

    @Resource
    private PrivilegeInterceptor privilegeInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(privilegeInterceptor).addPathPatterns("/**").excludePathPatterns("/login", "/error");
        super.addInterceptors(registry);
    }

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
    }


    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.extendMessageConverters(converters);
    }

}
