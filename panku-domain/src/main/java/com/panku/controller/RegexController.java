package com.panku.controller;

import com.panku.util.RegexUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: uaike
 * @create: 2020-11-25
 */
@RestController
@RequestMapping("/data")
public class RegexController {

    /**
     * 验证输入
     * http://localhost:8080/data/inputValidation/?input=163@163.com&type=email
     * @param input
     * @param type
     * @return
     */
    @GetMapping("/inputValidation")
    public boolean inputValidation(@RequestParam String input, @RequestParam String type){
        return RegexUtils.regularFilter(input, type);
    }

}
