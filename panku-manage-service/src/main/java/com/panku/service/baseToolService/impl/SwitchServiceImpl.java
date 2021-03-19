package com.panku.service.baseToolService.impl;

import com.panku.service.baseToolService.SwitchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description:
 * @author: uaike
 * @create: 2021-03-04
 */
@Slf4j
@Service
public class SwitchServiceImpl implements SwitchService {

    @Override
    public int switchByDate(String startTime, String endTime) {
        //格式化时间
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date startTimeDate = format.parse(startTime);
            Date endTimeDate = format.parse(endTime);
            Date nowDate = new Date();
            if(startTimeDate.before(nowDate)) {
                //开始时间 在 当前时间之前 返回-1
                return -1;
            }
            if(endTimeDate.after(nowDate)) {
                //结束时间 在 当前时间之后 1
                return 1;
            }
        } catch (Exception e) {
            log.error(">>>>> Switch Service exception >>>::", e);
            return 1;
        }
        return 0;
    }
}
