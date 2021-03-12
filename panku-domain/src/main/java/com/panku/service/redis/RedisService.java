package com.panku.service.redis;

/**
 * @description:
 * @author: uaike
 * @create: 2021-03-11
 */
public interface RedisService {

    void getTokenData();

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

}
