package com.panku.controller;

import com.panku.config.RequiredLogin;
import com.panku.dto.account.request.AccountRequestDTO;
import com.panku.dto.account.response.LoginResponse;
import com.panku.dto.user.CustomerDTO;
import com.panku.service.user.UserService;
import com.panku.util.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @description:
 * @author: uaike
 * @create: 2021-03-11
 */
@Slf4j
@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Resource
    private UserService userService;

    @PostMapping("/loginPhAndPwd")
    public ResultResponse login(@RequestBody AccountRequestDTO requestDTO){
        LoginResponse customer = userService.loginPhAndPwd(requestDTO);
        if (Objects.nonNull(customer)){
            return ResultResponse.success(customer);
        }
        return ResultResponse.error(1,"获取用户信息异常，登录失败");
    }

    @RequiredLogin
    @PostMapping("/queryAllUsers")
    public ResultResponse queryAllUsers(){
        List<CustomerDTO> customers = userService.queryAllUsers();
        if (Objects.nonNull(customers)){
            return ResultResponse.success(customers);
        }
        return ResultResponse.error(1,"获取用户信息异常，请重试！");
    }

    /**
     * 增加用户
     * @return
     */
    @RequiredLogin
    @PostMapping("/addUser")
    public ResultResponse queryAllUsers(@RequestBody CustomerDTO customerDTO){
        CustomerDTO customer = null;
        try {
            customer = userService.addUser(customerDTO);
        } catch (Exception e) {
            log.error(">>>>>>>add user exception >>>>>", e);
            return ResultResponse.error(e.getCause().getMessage());
        }
        return ResultResponse.success(customer);
    }

    /**
     * 删除用户
     * @param customerDTO
     * @return
     */
    @RequiredLogin
    @PostMapping("/deleteUser")
    public ResultResponse deleteUser(@RequestBody CustomerDTO customerDTO){
        Boolean result = userService.deleteUser(customerDTO.getUserId());
        return ResultResponse.success(result);
    }

    /**
     * 更新用户
     * @param customerDTO
     * @return
     */
    @RequiredLogin
    @PostMapping("/updateUser")
    public ResultResponse updateUser(@RequestBody CustomerDTO customerDTO){
        try {
            userService.updateUser(customerDTO);
        } catch (Exception e) {
            log.error(">>>>>>>add user exception >>>>>", e);
            return ResultResponse.error(e.getCause().getMessage());
        }
        return ResultResponse.success();
    }


}
