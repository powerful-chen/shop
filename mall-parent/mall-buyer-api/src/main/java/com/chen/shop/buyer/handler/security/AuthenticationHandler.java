package com.chen.shop.buyer.handler.security;

import com.chen.shop.common.security.AuthUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @ClassName AuthenticationHandler
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/16 20:25
 */
//获取用户信息处理
@Component
public class AuthenticationHandler {

    //获取当前用户信息
    public AuthUser getAuthUser() {
        //获取spring security 权限信息,如果token有权限,在这里就会得到内容
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        Object object = authentication.getDetails();
        if (object instanceof AuthUser) {
            return (AuthUser) authentication.getDetails();
        }
        return null;
    }
}
