package com.panku.dto.breadCrumb;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: uaike
 * @create: 2021-04-02
 */
@Data
public class RouterDTO {

    private String breadcrumbId;

    private String path;

    private String component;

    private String name;

    private String routerName;

    private Integer level;

    private Integer sort;

    private String parentId;

    private List<RouterDTO> children;

    private String auth;

    private List<String> breadcrumbName;

}
