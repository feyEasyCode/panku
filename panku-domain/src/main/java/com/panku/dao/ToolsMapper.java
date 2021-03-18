package com.panku.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @description: query tools info
 * @author: uaike
 * @create: 2020-12-02
 */
@Repository
public interface ToolsMapper {

    /**
     * 自定义查询
     * @return
     */
    Integer selectInfoSum();

    /**
     * 自定义查询
     * @return
     */
    @Select("SELECT count(id) as num from tools")
    Integer selectToolsCount();

}
