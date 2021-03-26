package com.panku.service.token;

import com.panku.dto.BaseRequestDTO;
import com.panku.dto.TokenResponseDTO;
import com.panku.dto.redis.RedisUserInfoDTO;

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

    TokenResponseDTO generateToken(RedisUserInfoDTO requestDTO);
}
