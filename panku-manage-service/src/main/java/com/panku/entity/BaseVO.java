package com.panku.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description:
 * @author: uaike
 * @create: 2021-06-29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseVO {

    @Column(name = "create_time", type = MySqlTypeConstant.TIMESTAMP, comment = "创建时间")
    private Date createTime;

    @Column(name = "modified_time", comment = "修改时间")
    private Date modifiedTime;

}
