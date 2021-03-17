package com.panku.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @description:
 * @author: uaike
 * @create: 2021-03-17
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Resource
    AuthorizationInterceptor authorizationInterceptor;

    @Resource
    UserLoginInterceptor userLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义的拦截器
        registry.addInterceptor(authorizationInterceptor).addPathPatterns("/**");
        registry.addInterceptor(userLoginInterceptor).addPathPatterns("/**");
    }
}
