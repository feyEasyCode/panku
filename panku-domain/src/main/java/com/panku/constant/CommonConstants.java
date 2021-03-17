package com.panku.constant;

/**
 * @description:
 * @author: uaike
 * @create: 2021-03-12
 */
public interface CommonConstants {

    interface JWT {

        String JWT_ID = "jwt";

        String REDISPREFIX = "redis_Prefix:";
    }

    interface REDIS {
        //jwt redis
        String REDIS_TOKEN_PREFIX = "jwtToken:";
        String USER_ID = "userId";
        String USER_NAME = "userName";
        String MOBILE = "mobile";
        String EMAIL = "email";
    }
}
