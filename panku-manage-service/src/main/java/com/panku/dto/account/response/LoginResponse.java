package com.panku.dto.account.response;

import com.panku.dto.TokenResponseDTO;
import com.panku.dto.user.CustomerDTO;
import lombok.Data;

/**
 * @description:
 * @author: uaike
 * @create: 2021-03-11
 */
@Data
public class LoginResponse {

    private CustomerDTO customer;

    private TokenResponseDTO jwtToken;

}
