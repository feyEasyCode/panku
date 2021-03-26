package com.panku.dto.user;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import lombok.Data;

import java.util.Date;

/**
 * @description:
 * @author: uaike
 * @create: 2021-03-25
 */
@Data
public class CustomerDTO {

    private String userId;

    private String name;

    private String mobile;

    private String email;

    private String address;

    private String gender;

    private String headImg;

    private String userType;

    private String userStatus;

    private Date createTime;

    private Date modifiedTime;

    private String jwt;

}
