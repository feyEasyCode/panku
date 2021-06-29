package com.panku.entity.product;

import com.gitee.sunchenbin.mybatis.actable.annotation.*;
import com.panku.entity.BaseVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: uaike
 * @create: 2021-06-29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "base_product")
public class BaseProduct extends BaseVO {

    @IsAutoIncrement   //自增
    @IsKey             //actable主键注解
    @Column(comment = "产品表主键")//对应数据库字段，不配置name会直接采用属性名作为字段名comment是注解
    private Integer id;

    @Unique
    @Column(name = "sku_id", comment = "产品id")
    private String skuId;

    @Column(name = "catalog_id", comment = "目录")
    private String catalogId;

    @Column(name = "name", comment = "商品名")
    private String name;

    @Column(name = "short_name", comment = "商品简名")
    private String shortName;

    @Column(name = "short_desc", comment = "商品简述")
    private String shortDesc;

    @Column(name = "attribute", comment = "商品属性")
    private String attribute;

    @Column(name = "type", comment = "商品分类")
    private String type;

    @Column(name = "details", comment = "商品详情")
    private String details;
}
