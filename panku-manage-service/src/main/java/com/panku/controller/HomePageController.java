package com.panku.controller;

import com.panku.config.Authorization;
import com.panku.config.RequiredLogin;
import com.panku.dto.breadCrumb.BreadcrumbResponseDTO;
import com.panku.entity.HomeCommon;
import com.panku.service.breadcrumbService.BreadcrumbService;
import com.panku.util.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/api/common")
public class HomePageController {

    @Resource
    private BreadcrumbService breadcrumbService;

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

    @Authorization
    @RequiredLogin
    @PostMapping("/queryBreadcrumb")
    public ResultResponse queryBreadcrumb(){

        BreadcrumbResponseDTO  responseDTO = breadcrumbService.queryBreadcrumb();

        return ResultResponse.success(responseDTO);
    }

}
