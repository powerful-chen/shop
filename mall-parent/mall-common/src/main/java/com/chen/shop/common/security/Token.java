package com.chen.shop.common.security;

import lombok.Data;

/**
 * @ClassName Token
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/15 14:45
 */
//token实体类
@Data
public class Token {
    //访问token
    private String accessToken;
    //刷新token
    private String refreshToken;
}
