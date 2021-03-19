package com.panku;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @description:
 * @author: uaike
 * @create: 2021-03-10
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.panku.dao","com.gitee.sunchenbin.mybatis.actable.dao.*"})
@ComponentScan(basePackages = {"com.panku.*","com.gitee.sunchenbin.mybatis.actable.manager.*"})
public class PanKuManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(PanKuManageApplication.class, args);
    }

}
