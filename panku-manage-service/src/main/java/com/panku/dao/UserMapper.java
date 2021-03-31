package com.panku.dao;

import com.panku.entity.Customer;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description: query user info
 * @author: uaike
 * @create: 2020-12-07
 */
@Repository
public interface UserMapper {

    /**
     * mapper xml方式实现SQL
     * @param mobileNum
     * @param passWord
     * @return
     */
//    @Select(" SELECT * FROM users Where mobile=#{mobileNum} AND pass_word=#{passWord}")
    List<Customer> loginByPhAndPwd(String mobileNum, String passWord);

    /**
     * 注解方式实现SQL语句
     * @param userId
     * @param passWord
     * @return
     */
    @Select("SELECT * FROM users Where mobile=#{userId} AND pass_word=#{passWord}")
    List<Customer> queryUser(String userId, String passWord);

    /**
     *
     * @param userId
     * @return
     */
    @Select("SELECT * FROM users Where mobile=#{userId}")
    List<Customer> queryUserInfo(String userId);


    /**
     * 查询所有用户列表
     * @return
     */
    List<Customer> queryAllUsers();


    /**
     * 新增用户
     * @param customer
     * @return
     */
    int insertUser(Customer customer);


    /**
     * 删除用户
     * @param userId
     * @return
     */
    void deleteUser(String userId);

    /**
     * 更新数据
     * @param customer
     */
    void updateUser(Customer customer);
}
