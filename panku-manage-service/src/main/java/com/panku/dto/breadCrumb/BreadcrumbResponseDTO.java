package com.panku.dto.breadCrumb;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: uaike
 * @create: 2021-03-23
 */
@Data
public class BreadcrumbResponseDTO {

    private List<RouterDTO> breadcrumbs;

}
