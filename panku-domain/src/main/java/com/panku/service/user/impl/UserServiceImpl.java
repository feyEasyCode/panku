package com.panku.service.user.impl;

import com.panku.dao.UserMapper;
import com.panku.dto.account.request.AccountRequestDTO;
import com.panku.entity.Customer;
import com.panku.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @description:
 * @author: uaike
 * @create: 2021-03-11
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;




    @Override
    public Customer loginPhAndPwd(AccountRequestDTO requestDTO){

        List<Customer> customers = userMapper.loginByPhAndPwd(requestDTO.getMobileNum(), requestDTO.getPassWord());

        if (CollectionUtils.isEmpty(customers) || customers.size()>1){
            return null;
        }

        return customers.get(0);
    }

}
