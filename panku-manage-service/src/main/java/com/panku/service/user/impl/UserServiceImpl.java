package com.panku.service.user.impl;

import com.panku.convertor.UserConvertor;
import com.panku.dao.UserMapper;
import com.panku.dto.TokenResponseDTO;
import com.panku.dto.account.request.AccountRequestDTO;
import com.panku.dto.account.response.LoginResponse;
import com.panku.dto.redis.RedisUserInfoDTO;
import com.panku.dto.user.CustomerDTO;
import com.panku.entity.Customer;
import com.panku.service.commonservice.CommonService;
import com.panku.service.redis.RedisService;
import com.panku.service.token.TokenService;
import com.panku.service.user.UserService;
import com.sun.org.apache.regexp.internal.RE;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @Resource
    private TokenService tokenService;

    @Resource
    private CommonService commonService;

    @Override
    public LoginResponse loginPhAndPwd(AccountRequestDTO requestDTO){
        LoginResponse response = new LoginResponse();
        List<Customer> customers = userMapper.loginByPhAndPwd(requestDTO.getMobileNum(), requestDTO.getPassWord());
        if (CollectionUtils.isEmpty(customers) || customers.size()>1){
            return null;
        }
        //用户信息存入Redis
        CustomerDTO customer = UserConvertor.userConvertor(customers.get(0));
        if (Objects.nonNull(customer)){
            RedisUserInfoDTO userInfoDTO = new RedisUserInfoDTO();
            userInfoDTO.setUserId(customer.getUserId());
            userInfoDTO.setUserName(customer.getName());
            userInfoDTO.setMobile(customer.getMobile());
            userInfoDTO.setEmail(customer.getEmail());
            TokenResponseDTO tokenResponseDTO = tokenService.generateToken(userInfoDTO);
            response.setCustomer(customer);
            response.setJwtToken(tokenResponseDTO);
        }
        return response;
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


    @Override
    public List<CustomerDTO> queryAllUsers() {
        List<Customer> customers = userMapper.queryAllUsers();
        if (CollectionUtils.isEmpty(customers)){
            return null;
        }
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for (Customer customer : customers){
            CustomerDTO customerDTO = UserConvertor.userConvertor(customer);
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }

    @Override
    public CustomerDTO addUser(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setEmail(customerDTO.getEmail());
        customer.setMobile(customerDTO.getMobile());
        customer.setGender(customerDTO.getGender());
        customer.setAddress(customerDTO.getAddress());
        customer.setHeadImg(customerDTO.getHeadImg());
        customer.setName(customerDTO.getName());
        customer.setPassWord(customerDTO.getJwt());
        customer.setUserType(customerDTO.getUserType());
        customer.setUserStatus("0");
        //获取数据库id
        Long nextId = commonService.nextId();
        Long memebr = 90000000L;
        customer.setUserId(String.valueOf(nextId+memebr));
        int result = userMapper.insertUser(customer);
        if (result == 1 && customer.getId() != 0){
            log.info(">>>>>>>INSERT RESULT >>>>>::" + result);
            customerDTO.setUserId(customer.getUserId());
            customerDTO.setJwt("");
        }
        return customerDTO;
    }

    @Override
    public Boolean deleteUser(String userId) {
        try {
            userMapper.deleteUser(userId);
        } catch (Exception e){
            log.info(">>>>>>DELETE USER EXCEPTION >>>>>::"+ userId);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public void updateUser(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setEmail(customerDTO.getEmail());
        customer.setUserId(customerDTO.getUserId());
        customer.setMobile(customerDTO.getMobile());
        customer.setGender(customerDTO.getGender());
        customer.setAddress(customerDTO.getAddress());
        customer.setHeadImg(customerDTO.getHeadImg());
        customer.setName(customerDTO.getName());
        customer.setPassWord(customerDTO.getJwt());
        customer.setUserType(customerDTO.getUserType());
        customer.setUserStatus("0");
        userMapper.updateUser(customer);
    }
}
