package com.panku.controller;

import com.panku.dao.ToolsMapper;
import com.panku.entity.Tools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @description:
 * @author: uaike
 * @create: 2020-12-06
 */
@Slf4j
@RestController
@RequestMapping("/data")
public class ToolsController {

    @Autowired
    private ToolsMapper toolsMapper;


    @GetMapping("/create")
    public String createTool(){

        Tools tool = new Tools();
        tool.setName("测试date");
        tool.setAliseName("时间格式转换工具");
        tool.setType("转换");
        tool.setTag("时间");
        tool.setCreateTime(new Date());
        tool.setModifiedTime(new Date());

        //原始插入方法
//        toolsMapper.insert(tool);

        //注解方式自定义SQL语句
        Integer count = toolsMapper.selectToolsCount();
        log.info("COUNT>>>>>" + count);

        //xml方式自定义SQL语句
        Integer sum = toolsMapper.selectInfoSum();
        log.info("SUM>>>>>" + sum);

        return null;
    }
}
