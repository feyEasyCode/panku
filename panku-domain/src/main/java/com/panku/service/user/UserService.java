package com.panku.service.user;

import com.panku.dto.account.request.AccountRequestDTO;
import com.panku.entity.Customer;

/**
 * @description:
 * @author: uaike
 * @create: 2021-03-11
 */
public interface UserService {

    /**
     * 手机号 + 密码登录
     * @param requestDTO
     * @return
     */
    Customer loginPhAndPwd(AccountRequestDTO requestDTO);

}
