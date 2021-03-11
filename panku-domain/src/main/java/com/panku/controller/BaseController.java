package com.panku.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: uaike
 * @create: 2021-03-10
 */
@RestController
@RequestMapping("/token")
public class BaseController {

    @PostMapping("/getToken")
    public Object getToken(){


        return null;

    }

}
