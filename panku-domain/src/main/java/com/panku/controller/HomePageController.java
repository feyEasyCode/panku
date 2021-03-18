package com.panku.controller;

import com.panku.config.Authorization;
import com.panku.entity.HomeCommon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/common")
public class HomePageController {

    @GetMapping("test")
    public String Home(){
        return "Welcome to Kaleidoscope！";
    }

    /**
     * 页面通用数据获取
     * @return
     */
    @Authorization
    @GetMapping("/getCommonData")
    public HomeCommon getCommonData(){
        HomeCommon homeCommon = new HomeCommon();
        homeCommon.setDescribe("欢迎来到万花筒项目，持续更新中，敬请期待~");
        homeCommon.setUrl("www.uaike.com");
        log.info("加入log注解");
        return homeCommon;
    }

}
