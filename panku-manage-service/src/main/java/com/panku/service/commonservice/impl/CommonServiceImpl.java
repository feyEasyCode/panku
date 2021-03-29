package com.panku.service.commonservice.impl;

import com.panku.dao.CommonMapper;
import com.panku.service.commonservice.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description: 通用查询方法
 * @author: uaike
 * @create: 2021-03-29
 */
@Slf4j
@Service
public class CommonServiceImpl implements CommonService {

    @Resource
    private CommonMapper commonMapper;

    @Override
    public Long nextId() {

        return commonMapper.getNextId();

    }
}
