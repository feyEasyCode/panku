package com.panku.config;

import com.google.common.net.HttpHeaders;
import com.panku.constant.CommonConstants;
import com.panku.service.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @description:
 * @author: uaike
 * @create: 2021-03-12
 */
@Slf4j
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private RedisService redisService;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)handler;
        Method method=handlerMethod.getMethod();
        //检查是否有Authorization注释，有则进行认证
        if (!method.isAnnotationPresent(Authorization.class)) {
            return true;
        }
        //根据传入jession 获取数据
        String jwtToken = "";
        redisService.getTokenData();
        log.info("======interceptor user jwtToken========"+jwtToken);
        if(StringUtils.isEmpty(jwtToken) || !redisTemplate.hasKey(CommonConstants.JWT.REDISPREFIX + jwtToken)){
            log.info("======401 unauthorized========"+jwtToken);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
            response.getWriter().println("{\"code\":\"401\",\"message\":\"401 unauthorized\"}");
            return false;
        }
        return true;
    }
}
