package com.panku.dto.breadCrumb;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: uaike
 * @create: 2021-03-23
 */
@Data
public class BreadcrumbDTO {

    private String breadcrumbId;

    private String breadcrumbName;

    private Integer level;

    private Integer sort;

    private String url;

    private String parentId;

    private List<BreadcrumbDTO> subBreadcrumb;

}
