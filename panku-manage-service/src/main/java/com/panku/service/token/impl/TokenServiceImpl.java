package com.panku.service.token.impl;

import com.panku.constant.CommonConstants;
import com.panku.dto.TokenResponseDTO;
import com.panku.dto.redis.RedisUserInfoDTO;
import com.panku.service.redis.RedisService;
import com.panku.service.token.TokenService;
import com.panku.util.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.core.util.UuidUtil;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: uaike
 * @create: 2021-03-16
 */
@Service
@Slf4j
public class TokenServiceImpl  implements TokenService {

    private static final String JWT_SECRET = "RxrrO92uvk7S65M3IDlrOZrzoPce0hfM";

    private static final Long JWT_EXPIRED = 1800000L;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private RedisService redisService;


    @Override
    public String createJWT(String token, String userId) {
        // 指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        // 生成JWT的时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        // 创建payload的私有声明（根据特定的业务需要添加，如果要拿这个做验证，一般是需要和jwt的接收方提前沟通好验证方式的）
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        // 生成签名的时候使用的秘钥secret，切记这个秘钥不能外露哦。它就是你服务端的私钥，在任何场景都不应该流露出去。
        // 一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
        SecretKey key = generalKey();
        // 下面就是在为payload添加各种标准声明和私有声明了
        // 这里其实就是new一个JwtBuilder，设置jwt的body
        // 如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
        // 设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
        // iat: jwt的签发时间
        // 设置签名使用的签名算法和签名使用的秘钥
        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setId(token)
                .setIssuedAt(now)
                .signWith(signatureAlgorithm, key);

        // 设置过期时间
        if (JWT_EXPIRED >= 0) {
            long expMillis = nowMillis + JWT_EXPIRED;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    /**
     * 验证JWT
     *
     * @return
     */
    @Override
    public boolean validateJWT() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        boolean validateJWT = false;
        if(StringUtils.isNotEmpty(request.getHeader(CommonConstants.JWT.JWT_ID))){
            String jwt = String.valueOf(request.getHeader(CommonConstants.JWT.JWT_ID));
            log.info("getJwtToken jwt:"+jwt);
            this.validateJWT(jwt);
            Claims claims = null;
            try {
                claims = parserJWT(jwt);
                String jwtToken =  claims.get("jwtToken").toString();
                log.info(">>>>>>>>validateJWT >>>>>>::" + jwtToken);
                String redisKey = CommonConstants.REDIS.REDIS_TOKEN_PREFIX + jwtToken;
                if (redisTemplate.hasKey(redisKey)){
                    return true;
                }
            } catch (Exception e) {
                log.error("JWTUtil getValue error:"+e.getMessage());
            }
        }
        return validateJWT;
    }

    @Override
    public String getToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String jwtToken = "";
        if(StringUtils.isNotEmpty(request.getHeader(CommonConstants.JWT.JWT_ID))) {
            String jwt = String.valueOf(request.getHeader(CommonConstants.JWT.JWT_ID));
            log.info("getJwtToken jwt:" + jwt);
            this.validateJWT(jwt);
            Claims claims = null;
            try {
                claims = parserJWT(jwt);
                jwtToken = claims.get("jwtToken").toString();
            } catch (Exception e) {
                log.error("JWTUtil getValue error:" + e.getMessage());
            }
        }
        return jwtToken;
    }

    @Override
    public boolean validateJWT(String jwtStr) {
        boolean flag=false;
        Claims claims = null;
        try {
            claims = parserJWT(jwtStr);
            String jwtToken =  claims.get("jwtToken").toString();
            log.info(">>>>>>>>validateJWT >>>>>>::" + jwtToken);
            if (redisTemplate.hasKey(jwtToken)){
                return true;
            }
        } catch (Exception e) {
            log.error("validate JWT error", e);
        }
        return flag;
    }

    @Override
    public TokenResponseDTO generateToken(RedisUserInfoDTO requestDTO) {
        TokenResponseDTO responseDTO = new TokenResponseDTO();
        //生产随机数据
        String token = UuidUtil.getTimeBasedUuid().toString().replace("-", "");
        String jwtToken = null;
        Boolean jwtFlag = Boolean.FALSE;
        try {
            jwtToken = JwtUtils.createJWT(token, requestDTO.getUserId());
            log.info(">>>>>>>TOKEN>>>>>>" + token);
            if (StringUtils.isNotEmpty(jwtToken)){
                requestDTO.setJwtToken(token);
                jwtFlag = redisService.saveUserInfo(requestDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (jwtFlag){
            responseDTO.setToken(token);
            responseDTO.setJwt(jwtToken);
            responseDTO.setExpireTime(18000);
        }
        return responseDTO;
    }

    /**
     * 验证JWT
     *
     * @param jwt
     * @return
     */
    private Claims parserJWT(String jwt) {
        //签名秘钥，和生成的签名的秘钥一模一样
        SecretKey key = generalKey();
        log.info(">>>>>>>KEY>>>>>" + key);
        //设置需要解析的jwt
        //得到DefaultJwtParser
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt).getBody();
    }


    /**
     * 由字符串生成加密key
     *
     * @return
     */
    private SecretKey generalKey() {
        String stringKey = JWT_SECRET;
        // 本地的密码解码
        byte[] encodedKey = Base64.decodeBase64(stringKey);
        // 根据给定的字节数组使用AES加密算法构造一个密钥
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }
}
