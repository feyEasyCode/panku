package com.panku.convertor;

import com.panku.dto.user.CustomerDTO;
import com.panku.entity.Customer;

/**
 * @description:
 * @author: uaike
 * @create: 2021-03-26
 */
public class UserConvertor {

    public static CustomerDTO userConvertor(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setUserId(customer.getUserId());
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setMobile(customer.getMobile());
        customerDTO.setHeadImg(customer.getHeadImg());
        customerDTO.setCreateTime(customer.getCreateTime());
        customerDTO.setModifiedTime(customer.getModifiedTime());
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setGender(customer.getGender());
        customerDTO.setUserType(customer.getUserType());
        customerDTO.setUserStatus(customer.getUserStatus());
        return customerDTO;
    }

}
