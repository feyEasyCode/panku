package com.panku.dto;

import lombok.Data;

/**
 * @description:
 * @author: uaike
 * @create: 2021-03-11
 */
@Data
public class TokenResponseDTO {

    private String type;

    private String token;

    private long expireTime;

    private String jwt;

}
