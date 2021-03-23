package com.panku.service.breadcrumbService;


import com.panku.dto.breadCrumb.BreadcrumbResponseDTO;

/**
 * @description:
 * @author: uaike
 * @create: 2021-03-23
 */
public interface BreadcrumbService {

    /**
     * 获取导航数据
     */
    BreadcrumbResponseDTO queryBreadcrumb();

}
