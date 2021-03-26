package com.panku.controller;

import com.panku.dto.TokenResponseDTO;
import com.panku.dto.redis.RedisUserInfoDTO;
import com.panku.service.token.TokenService;
import com.panku.util.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @description:
 * @author: uaike
 * @create: 2021-03-10
 */
@Slf4j
@RestController
@RequestMapping("/token")
public class BaseController {

    @Resource
    private TokenService tokenService;


    private static final long TOKEN_EXPIRED_TIME = 1800000L;

    @PostMapping("/getToken")
    public ResultResponse getToken(@RequestBody RedisUserInfoDTO requestDTO) throws Exception {
        TokenResponseDTO responseDTO = tokenService.generateToken(requestDTO);
        if (Objects.isNull(responseDTO)){
            return ResultResponse.error(1, "认证失败，请重试");
        }
        return ResultResponse.success("接口调用成功", responseDTO);
    }

}
