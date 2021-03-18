package com.panku.util;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @description:
 * @author: uaike
 * @create: 2021-03-04
 */
@Slf4j
public class DateUtils {

    /**
     * date 转 String 输出
     * @param date
     * @return
     */
    private static String currentTimeYMDHMS(Date date){

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return format.format(date);
    }


    /**
     * 获取当前月份第一天
     * return 格式化的日期
     */
    public static String getFirstDayOfThisMonth() {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return myFormatter.format(cal.getTime()) + " 00:00:00";
    }


    /**
     * 获取当前月份最后一天
     * return 格式化的日期
     */
    public static String getMaxDayOfThisMonth() {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, 1);
        cal.roll(Calendar.DATE, -1);
        return myFormatter.format(cal.getTime()) + " 23:59:59";
    }

}
