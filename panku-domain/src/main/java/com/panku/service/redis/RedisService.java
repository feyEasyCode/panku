package com.panku.service.redis;

import com.panku.dto.redis.RedisUserInfoDTO;

/**
 * @description:
 * @author: uaike
 * @create: 2021-03-11
 */
public interface RedisService {

    boolean saveJwt(String jwtToken);

    boolean validateJWT();
    /**
     * 保存key-value 到redis
     * @param key
     * @param value
     * @return
     */
    boolean saveDataToRedis(String key, Object value);

    /**
     * 保存key-value 到redis 并设置缓存时间
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    boolean saveDataAndSetExpire(String key, Object value, Long expireTime);

    /**
     * 保存用户信息到redis
     * @param userInfoDTO
     * @return
     */
    boolean saveUserInfo(RedisUserInfoDTO userInfoDTO);

    /**
     * 从redis获取用户信息
     */
    RedisUserInfoDTO getUserInfoByRedis();
}
