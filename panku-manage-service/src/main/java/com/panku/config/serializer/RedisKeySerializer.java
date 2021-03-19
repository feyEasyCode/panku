package com.panku.config.serializer;

import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;
import java.util.Objects;

/**
 * @description: 将redis key 序列化为字符串形式，解决乱码问题
 * @author: uaike
 * @create: 2021-03-17
 */
public class RedisKeySerializer implements RedisSerializer<Object> {

    private final Charset charset;

    private final ConversionService converter;

    public RedisKeySerializer() {
        this(Charset.forName("UTF8"));
    }

    public RedisKeySerializer(Charset charset) {
        Objects.requireNonNull(charset, "Charset must not be null");
        this.charset = charset;
        this.converter = DefaultConversionService.getSharedInstance();
    }

    @Override
    public byte[] serialize(Object object) throws SerializationException {
        Objects.requireNonNull(object, "redis key is null");
        String key;
        if (object instanceof SimpleKey) {
            key = "";
        } else if (object instanceof String) {
            key = (String)object;
        } else {
            key = converter.convert(object, String.class);
        }
        return key.getBytes(this.charset);
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        return (bytes == null ? null : new String(bytes, charset));
    }
}
