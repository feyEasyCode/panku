package com.panku.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.panku.config.serializer.RedisKeySerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @description:
 * @author: uaike
 * @create: 2021-03-17
 */
@Configuration
public class RedisCacheConfig extends CachingConfigurerSupport {

    /**
     *
     * @param redisConnFactory
     * @param redisSerializer
     * @return
     */
    @Bean(name = "redisTemplate")
    @ConditionalOnMissingBean(RedisTemplate.class)
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnFactory, RedisSerializer<Object> redisSerializer) {
        // 配置redisTemplate
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnFactory);
        RedisKeySerializer redisKeySerializer = new RedisKeySerializer();
        // key序列化
        redisTemplate.setKeySerializer(redisKeySerializer);
        // value序列化
        redisTemplate.setValueSerializer(redisSerializer);
        // Hash key序列化
        redisTemplate.setHashKeySerializer(redisSerializer);
        // Hash value序列化
        redisTemplate.setHashValueSerializer(redisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * redis value 值 序列化
     *
     * @return RedisSerializer
     */
    @Bean
    @ConditionalOnMissingBean(RedisSerializer.class)
    public RedisSerializer<Object> redisSerializer() {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer =new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        om.registerModule(new JavaTimeModule());
        jackson2JsonRedisSerializer.setObjectMapper(om);

        return jackson2JsonRedisSerializer;
    }

}
