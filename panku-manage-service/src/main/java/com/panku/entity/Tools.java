package com.panku.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsAutoIncrement;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.command.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description:
 * @author: uaike
 * @create: 2020-12-02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tools")
public class Tools extends BaseModel {

    @IsAutoIncrement   //自增
    @IsKey             //actable主键注解
    @Column(comment = "工具ID")//对应数据库字段，不配置name会直接采用属性名作为字段名comment是注解
    private Long id;

    @Column(comment = "工具名")
    private String name;

    @Column(name = "alise_name", comment = "别名")
    private String aliseName;

    @Column(comment = "描述")
    private String description;

    @Column(comment = "标签")
    private String tag;

    @Column(comment = "类型")
    private String type;

    @Column(name = "create_time", comment = "创建时间")
    private Date createTime;

    @Column(name = "modified_time", comment = "修改时间")
    private Date modifiedTime;
}
