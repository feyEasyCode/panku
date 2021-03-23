package com.panku.dao;

import com.panku.entity.Breadcrumb;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @author: uaike
 * @create: 2021-03-23
 */
@Repository
public interface BreadcrumbMapper {

    List<Breadcrumb>  queryBreadcrumb();

}
