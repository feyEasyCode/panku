package com.panku.service.redis;

import com.panku.dto.redis.RedisUserInfoDTO;

/**
 * @description:
 * @author: uaike
 * @create: 2021-03-11
 */
public interface RedisService {

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


    boolean saveUserInfo(RedisUserInfoDTO userInfoDTO);

}
