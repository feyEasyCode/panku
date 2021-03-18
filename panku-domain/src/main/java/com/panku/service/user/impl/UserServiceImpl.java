package com.panku.service.user.impl;

import com.panku.dao.UserMapper;
import com.panku.dto.account.request.AccountRequestDTO;
import com.panku.dto.redis.RedisUserInfoDTO;
import com.panku.entity.Customer;
import com.panku.service.redis.RedisService;
import com.panku.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @description:
 * @author: uaike
 * @create: 2021-03-11
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisService redisService;

    @Override
    public Customer loginPhAndPwd(AccountRequestDTO requestDTO){
        List<Customer> customers = userMapper.loginByPhAndPwd(requestDTO.getMobileNum(), requestDTO.getPassWord());
        if (CollectionUtils.isEmpty(customers) || customers.size()>1){
            return null;
        }
        //用户信息存入Redis
        RedisUserInfoDTO userInfoDTO = redisService.getUserInfoByRedis();
        userInfoDTO.setUserId(customers.get(0).getUserId());
        userInfoDTO.setUserName(customers.get(0).getName());
        userInfoDTO.setMobile(customers.get(0).getMobile());
        userInfoDTO.setEmail(customers.get(0).getEmail());
        redisService.saveUserInfo(userInfoDTO);
        return customers.get(0);
    }

    @Override
    public boolean isAnonymousUser() {
        RedisUserInfoDTO redisUserInfoDTO = redisService.getUserInfoByRedis();
        if (Objects.nonNull(redisUserInfoDTO) && StringUtils.isEmpty(redisUserInfoDTO.getUserId())){
            return true;
        }
        return false;
    }

    @Override
    public Customer getCurrentUser() {
        RedisUserInfoDTO redisUserInfoDTO = redisService.getUserInfoByRedis();
        if (Objects.isNull(redisUserInfoDTO) || StringUtils.isEmpty(redisUserInfoDTO.getUserId())){
            return null;
        }
        List<Customer> customers = userMapper.queryUserInfo(redisUserInfoDTO.getUserId());
        if (CollectionUtils.isEmpty(customers) || customers.size()>1){
            return null;
        }
        return customers.get(0);
    }
}
