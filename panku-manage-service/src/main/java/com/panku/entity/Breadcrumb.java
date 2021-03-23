package com.panku.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsAutoIncrement;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description:
 * @author: uaike
 * @create: 2021-03-23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "breadcrumb")
public class Breadcrumb {

    @IsAutoIncrement   //自增
    @IsKey             //actable主键注解
    @Column(comment = "导航表主键")//对应数据库字段，不配置name会直接采用属性名作为字段名comment是注解
    private Long id;

    @Column(name = "breadcrumb_id", comment = "导航ID")
    private String breadcrumbId;

    @Column(name = "breadcrumb_name", comment = "导航描述")
    private String breadcrumbName;

    @Column(name = "level", comment = "导航等级")
    private Integer level;

    @Column(name = "sort", comment = "导航排序")
    private Integer sort;

    @Column(name = "url", comment = "导航链接")
    private String url;

    @Column(name = "pid", comment = "主导航id")
    private String pid;

    @Column(name = "status", comment = "状态")
    private Integer status;

    @Column(name = "create_time", comment = "创建时间")
    private Date createTime;

    @Column(name = "modified_time", comment = "修改时间")
    private Date modifiedTime;
}
