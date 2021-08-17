package com.chen.shop.buyer.handler.security;

import com.chen.shop.common.security.AuthUser;

/**
 * @ClassName UserContext
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/16 20:19
 */
//用户上下文
public class UserContext {

    private static AuthenticationHandler authenticationHandler;

    public static void setHolder(AuthenticationHandler authenticationHandler) {
        UserContext.authenticationHandler = authenticationHandler;
    }


    public static AuthUser getCurrentUser() {
        return authenticationHandler.getAuthUser();
    }
}
