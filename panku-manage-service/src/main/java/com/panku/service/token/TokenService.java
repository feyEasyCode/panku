package com.panku.service.token;

/**
 * @description:
 * @author: uaike
 * @create: 2021-03-16
 */
public interface TokenService {

    String createJWT(String token, String userId);

    boolean validateJWT();

    boolean validateJWT(String jwtStr);

    String getToken();

}
