package com.panku.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: uaike
 * @create: 2021-03-11
 */
@Slf4j
public class JwtUtils {

    /**
     * 密钥
     */
    private static final String SECRET = "RxrrO92uvk7S65M3IDlrOZrzoPce0hfM";

    /**
     * 过期时间（单位：秒）
     **/
    private static final long EXPIRATION = 1800L;

    public static String createToken(Integer userId, String account, String password) {
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = JWT.create()
                // 添加头部
                .withHeader(map)
                // 放入用户的id
                .withAudience(String.valueOf(userId))
                // 可以将基本信息放到claims中
                .withClaim("account", account)
                .withClaim("password", password)
                // 超时设置,设置过期的日期
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION * 1000))
                // 签发时间
                .withIssuedAt(new Date())
                // SECRET加密
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }


}
