package com.panku.controller;

import com.panku.dto.date.DateRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 此类主要是针对常用时间转换代码的案例实现，及编码演示
 * 仅供参考或借鉴
 */
@Slf4j
@RestController
@RequestMapping("/date")
public class DateUtilsController extends BaseController {

    /**
     * 获取当前时间
     * @return
     */
    @GetMapping("/formatToRules")
    public String formatToRules(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String dateString = simpleDateFormat.format(new Date());
        return dateString;
    }

    /**
     * 执行格式 转换
     * @return
     * @throws ParseException
     */
    @GetMapping("/formatToRulesStr")
    private String groupTaskEndTimeFormate() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse("2020-09-24 23:59:59");
        SimpleDateFormat zz = new SimpleDateFormat("MM月dd日");
        String dateString = zz.format(date);
        return dateString;
    }

    /**
     * String 时间格式转Long time
     * @return
     * @throws ParseException
     */
    @GetMapping("/formatToRulesLong")
    private long stringTimeFormateBy() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse("2020-09-24 23:59:59");
        if (date == null) {
            return 0;
        } else {
            // date类型转成long类型
            long currentTime = date.getTime();
            return currentTime;
        }
    }

    /**
     * 当前时间往前或者往后推几日
     * dateParam：传入原始时间
     * days ：3 后三天；-3 前三天
     * hour：指定小时
     * minute：指定分钟
     * second：指定秒
     * @param requestDTO
     * @return
     */
    @PostMapping("/customDate")
    private String customDate(@RequestBody DateRequestDTO requestDTO) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        if (StringUtils.isNotEmpty(requestDTO.getDateParam())){
           date = format.parse(requestDTO.getDateParam());
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Integer days = requestDTO.getDays();
        cal.add(Calendar.DATE,days);
        if (null != requestDTO.getHour()){
            cal.set(Calendar.HOUR_OF_DAY,requestDTO.getHour());
        }
        if (null != requestDTO.getMinute()){
            cal.set(Calendar.MINUTE,requestDTO.getMinute());
        }
        if (null != requestDTO.getSecond()){
            cal.set(Calendar.SECOND,requestDTO.getSecond());
        }
        String afterDate = format.format(cal.getTime());
        return afterDate;
    }


}
