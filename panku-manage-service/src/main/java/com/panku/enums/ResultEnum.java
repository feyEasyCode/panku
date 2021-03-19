package com.panku.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description:
 * @author: uaike
 * @create: 2021-03-09
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {

    //请求成功
    SUCCESS(0,"请求成功"),

    //请求失败
    ERROR(1, "请求失败"),

    //参数缺失
    PARAM_ERROR(2, "参数缺失");

    private Integer code;

    private String message;

}
