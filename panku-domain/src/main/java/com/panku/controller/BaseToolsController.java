package com.panku.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: uaike
 * @create: 2020-12-29
 */
@Slf4j
@RestController
@RequestMapping("/tools")
public class BaseToolsController {

    @GetMapping("/create")
    public String getBaseToolsInfo(){



        return "success";
    }

}
