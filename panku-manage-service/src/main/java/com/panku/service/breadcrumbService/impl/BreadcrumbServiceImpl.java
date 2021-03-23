package com.panku.service.breadcrumbService.impl;

import com.panku.dao.BreadcrumbMapper;
import com.panku.dto.breadCrumb.BreadcrumbResponseDTO;
import com.panku.entity.Breadcrumb;
import com.panku.service.breadcrumbService.BreadcrumbService;
import com.panku.service.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: uaike
 * @create: 2021-03-23
 */
@Service
@Slf4j
public class BreadcrumbServiceImpl implements BreadcrumbService {

    @Resource
    private BreadcrumbMapper breadcrumbMapper;

    @Resource
    private RedisService redisService;


    @Override
    public BreadcrumbResponseDTO queryBreadcrumb() {
        BreadcrumbResponseDTO breadcrumbResponseDTO = new BreadcrumbResponseDTO();
        List<Breadcrumb> breadcrumbs = breadcrumbMapper.queryBreadcrumb();
        for (Breadcrumb breadcrumb : breadcrumbs){


        }
        return breadcrumbResponseDTO;
    }
}
