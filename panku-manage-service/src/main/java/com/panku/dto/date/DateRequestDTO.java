package com.panku.dto.date;

import lombok.Data;

/**
 * @description:
 * @author: uaike
 * @create: 2021-03-02
 */
@Data
public class DateRequestDTO {

    //时间入参
    private String dateParam;

    //天数
    private Integer days;

    //小时
    private Integer hour;

    //分
    private Integer minute;

    //秒
    private Integer second;

}
