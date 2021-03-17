package com.panku.service.redis.impl;

import com.panku.constant.CommonConstants;
import com.panku.dto.redis.RedisUserInfoDTO;
import com.panku.service.redis.RedisService;
import com.panku.service.token.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
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

    @Resource
    private TokenService tokenService;

    @Override
    public boolean validateJWT() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        boolean validateJWT = false;
        if(StringUtils.isNotEmpty(request.getHeader(CommonConstants.JWT.JWT_ID))){
            String jwt = String.valueOf(request.getHeader(CommonConstants.JWT.JWT_ID));
            log.info("getJwtToken jwt:"+jwt);
            try {
                validateJWT = tokenService.validateJWT(jwt);
            } catch (Exception e) {
                log.error("JWTUtil getValue error:"+e.getMessage());
            }
        }
        return validateJWT;
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

    @Override
    public boolean saveUserInfo(RedisUserInfoDTO userInfoDTO) {
        String jwtToken = tokenService.getToken();
        if (StringUtils.isEmpty(jwtToken)){
            return false;
        }
        //用户信息存储map
        Map<String, Object> userMap = new HashMap<>();
        if (StringUtils.isNotEmpty(userInfoDTO.getUserId())){
            userMap.put(CommonConstants.REDIS.USER_ID, userInfoDTO.getUserId());
        }
        if (StringUtils.isNotEmpty(userInfoDTO.getUserName())){
            userMap.put(CommonConstants.REDIS.USER_NAME, userInfoDTO.getUserName());
        }
        if (StringUtils.isNotEmpty(userInfoDTO.getMobile())){
            userMap.put(CommonConstants.REDIS.MOBILE, userInfoDTO.getMobile());
        }
        if (StringUtils.isNotEmpty(userInfoDTO.getEmail())){
            userMap.put(CommonConstants.REDIS.EMAIL, userInfoDTO.getEmail());
        }
        return saveDataToRedis(CommonConstants.REDIS.REDIS_TOKEN_PREFIX + jwtToken, userMap);
    }
}
