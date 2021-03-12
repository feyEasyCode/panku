package com.panku.service.redis.impl;

import com.panku.constant.CommonConstants;
import com.panku.service.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: uaike
 * @create: 2021-03-11
 */
@Service
@Slf4j
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void getTokenData() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String jwtToken = null;
        if(StringUtils.isNotEmpty(request.getHeader(CommonConstants.JWT.JSESSIONID))){
            String jwt = String.valueOf(request.getHeader(CommonConstants.JWT.JSESSIONID));
            log.info("getJwtToken jwt:"+jwt);
            try {
//                jwtToken = JWTUtil.getValue(jwt, MiniprogramConstants.Jwt.JWTKEY);
                log.info("get redis jwtToken:"+jwtToken);
            } catch (Exception e) {
                log.error("JWTUtil getValue error:"+e.getMessage());
            }
        }
    }

    @Override
    public boolean saveDataToRedis(String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean saveDataAndSetExpire(String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
