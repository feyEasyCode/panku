package com.panku.config;

import com.google.common.net.HttpHeaders;
import com.panku.constant.CommonConstants;
import com.panku.service.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        //根据传入jession 获取数据
        String jwtToken = "";
        redisService.getTokenData();
        log.info("======interceptor user jwtToken========"+jwtToken);
        //刷新用户信息
        if(StringUtils.isEmpty(jwtToken) || !redisTemplate.hasKey(CommonConstants.JWT.REDISPREFIX + jwtToken)){
            log.info("======401 unauthorized========"+jwtToken);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
            response.getWriter().println("{\"code\":\"401\",\"message\":\"401 unauthorized\"}");
            return false;
        }
        return true;

//        return super.preHandle(request, response, handler);
    }
}
