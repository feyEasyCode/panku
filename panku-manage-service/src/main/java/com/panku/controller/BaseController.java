package com.panku.controller;

import com.panku.constant.CommonConstants;
import com.panku.dto.BaseRequestDTO;
import com.panku.dto.TokenResponseDTO;
import com.panku.dto.redis.RedisUserInfoDTO;
import com.panku.service.redis.RedisService;
import com.panku.service.token.TokenService;
import com.panku.util.JwtUtils;
import com.panku.util.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.core.util.UuidUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    private RedisService redisService;

    @Resource
    private TokenService tokenService;


    private static final long TOKEN_EXPIRED_TIME = 1800000L;

    @PostMapping("/getToken")
    public ResultResponse getToken(@RequestBody BaseRequestDTO requestDTO) throws Exception {
        TokenResponseDTO responseDTO = new TokenResponseDTO();
        //生产随机数据
        String token = UuidUtil.getTimeBasedUuid().toString().replace("-", "");
        String jwtToken = JwtUtils.createJWT(token, requestDTO.getUserId());
        log.info(">>>>>>>TOKEN>>>>>>" + token);
        Boolean jwtFlag = Boolean.FALSE;
        if (StringUtils.isNotEmpty(jwtToken)){
            String redisKey = CommonConstants.REDIS.REDIS_TOKEN_PREFIX + token;
            RedisUserInfoDTO redisUserInfo = new RedisUserInfoDTO();
            redisUserInfo.setJwtToken(token);
            jwtFlag = redisService.saveUserInfo(redisUserInfo);
        }
        tokenService.validateJWT(jwtToken);
        responseDTO.setToken(token);
        responseDTO.setJwt(jwtToken);
        if (BooleanUtils.isFalse(jwtFlag)){
            return ResultResponse.error(1, "认证失败，请重试");
        }
        return ResultResponse.success("接口调用成功", responseDTO);
    }

}
