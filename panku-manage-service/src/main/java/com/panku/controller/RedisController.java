package com.panku.controller;

import com.alibaba.fastjson.JSON;
import com.panku.entity.Customer;
import com.panku.util.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: uaike
 * @create: 2021-03-11
 */
@RestController
@RequestMapping("redis")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/test")
    public ResultResponse test() {
        Customer user = new Customer();
        user.setUserId("980001");
        user.setName("测试用户数据");
        user.setPassWord("hhdhd");
        redisTemplate.opsForValue().set("json:customer", JSON.toJSONString(user));

        return ResultResponse.success(user);
    }
}
