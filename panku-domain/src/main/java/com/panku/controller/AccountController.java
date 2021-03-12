package com.panku.controller;

import com.panku.dto.account.request.AccountRequestDTO;
import com.panku.dto.redis.RedisUserInfoDTO;
import com.panku.entity.Customer;
import com.panku.service.redis.RedisService;
import com.panku.service.user.UserService;
import com.panku.util.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @description:
 * @author: uaike
 * @create: 2021-03-11
 */
@Slf4j
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @PostMapping("/loginPhAndPwd")
    public ResultResponse login(@RequestBody AccountRequestDTO requestDTO){
        Customer customer = userService.loginPhAndPwd(requestDTO);
        if (Objects.nonNull(customer)){
            RedisUserInfoDTO userInfoDTO = new RedisUserInfoDTO();
            userInfoDTO.setUserId(customer.getUserId());
            userInfoDTO.setUserName(customer.getName());
            userInfoDTO.setMobile(customer.getMobile());
            userInfoDTO.setEmail(customer.getEmail());
            return ResultResponse.success(customer);
        }
        return ResultResponse.error(1,"获取用户信息异常，登录失败");
    }


}
