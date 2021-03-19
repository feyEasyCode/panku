package com.panku.util;

import com.alibaba.fastjson.JSON;
import com.panku.enums.ResultEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @description: 统一返回封装
 * @author: uaike
 * @create: 2021-03-09
 */
@Slf4j
@Data
public class ResultResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    //返回code
    private Integer code;

    //是否成功
    private Boolean success;

    //返回调用信息
    private String message;

    //返回接口数据
    private Object data;

    public ResultResponse(Integer code, String message, Object data) {
        this.code = code;
        this.success = 0 == ResultEnum.SUCCESS.getCode() ? true : false ;
        this.message = message;
        this.data = data;
    }

    public static ResultResponse success(){
        return new ResultResponse(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), null);
    }

    public static ResultResponse success(String message, Object data){
        return new ResultResponse(ResultEnum.SUCCESS.getCode(), message, data);
    }

    public static ResultResponse success(Object data){
        return new ResultResponse(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), data);
    }

    public static ResultResponse error(String message){
        log.info("接口返回数据错误：code={}，message={}", ResultEnum.ERROR.getCode(), message);
        return new ResultResponse(ResultEnum.ERROR.getCode(), message, null);
    }

    public static ResultResponse error(Integer code, String message){
        log.info("接口返回数据异常：code={}，message={}", code, message);
        return new ResultResponse(code, message, null);
    }

    public static ResultResponse error(ResultEnum resultEnum){
        log.info("接口调用异常：code={}，message={}", resultEnum.getCode(), resultEnum.getMessage());
        return new ResultResponse(resultEnum.getCode(), resultEnum.getMessage(), null);
    }

    @Override
    public String toString(){
        return JSON.toJSONString(this);
    }
}
