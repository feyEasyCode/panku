package com.panku.controller;

import com.panku.dto.BaseRequestDTO;
import com.panku.dto.TokenResponseDTO;
import com.panku.service.redis.RedisService;
import com.panku.util.JwtUtils;
import com.panku.util.ResultResponse;
import com.sun.org.apache.regexp.internal.RE;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.core.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: uaike
 * @create: 2021-03-10
 */
@Slf4j
@RestController
@RequestMapping("/token")
public class BaseController {

    @Autowired
    private RedisService redisService;

    private static final String REDIS_PREFIX = "jwtToken:";
    private static final long TOKEN_EXPIRED_TIME = 1800L;

    @PostMapping("/getToken")
    public ResultResponse getToken(@RequestBody BaseRequestDTO requestDTO){
        TokenResponseDTO responseDTO = new TokenResponseDTO();
        //生产随机数据
        String jwtToken = UuidUtil.getTimeBasedUuid().toString().replace("-", "");
        String token = JwtUtils.generateToken(requestDTO.getUserId(), jwtToken);
        log.info(">>>>>>>TOKEN>>>>>>" + token);
        Boolean jwtFlag = Boolean.FALSE;
        if (StringUtils.isNotEmpty(token)){
            String redisKey = REDIS_PREFIX + jwtToken;
            if (StringUtils.isNotEmpty(requestDTO.getUserId())){
                redisKey = redisKey + "_" + requestDTO.getUserId();
            }
            jwtFlag = redisService.saveDataAndSetExpire(redisKey, "", TOKEN_EXPIRED_TIME);
        }
        responseDTO.setToken(jwtToken);
        responseDTO.setJwt(token);
        if (BooleanUtils.isFalse(jwtFlag)){
            return ResultResponse.error(1, "认证失败，请重试");
        }
        return ResultResponse.success("接口调用成功", responseDTO);
    }

}
