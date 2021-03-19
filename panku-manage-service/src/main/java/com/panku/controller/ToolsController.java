package com.panku.controller;

import com.panku.dao.ToolsMapper;
import com.panku.entity.Tools;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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

    /**
     *
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping ("/getIpAddr")
    public  String getIpAddr(final HttpServletRequest request)
            throws Exception {
        if (request == null) {
            throw (new Exception("getIpAddr method HttpServletRequest Object is null"));
        }
        String ipString = request.getHeader("x-forwarded-for");
        if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
            ipString = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
            ipString = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
            ipString = request.getRemoteAddr();
        }

        // 多个路由时，取第一个非unknown的ip
        final String[] arr = ipString.split(",");
        for (final String str : arr) {
            if (!"unknown".equalsIgnoreCase(str)) {
                ipString = str;
                break;
            }
        }

        return ipString;
    }
}
