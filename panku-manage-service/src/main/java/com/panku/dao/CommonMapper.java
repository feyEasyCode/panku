package com.panku.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: uaike
 * @create: 2021-03-29
 */
@Repository
public interface CommonMapper {

    @Select("SELECT auto_increment FROM information_schema.TABLES WHERE TABLE_SCHEMA='panku' AND TABLE_NAME='users'")
    Long getNextId();

}
