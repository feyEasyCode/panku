package com.panku.service.baseToolService;

import java.text.ParseException;

/**
 * @description:
 * @author: uaike
 * @create: 2021-03-04
 */
public interface SwitchService {


    /**
     * 判断时间区间
     * @param startTime
     * @param endTime
     * @return
     */
    int switchByDate(String startTime, String endTime) throws ParseException;

}
