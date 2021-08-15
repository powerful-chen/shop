package com.chen.shop.model.buyer.enums;

/**
 * @ClassName VerificationSourceEnum
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/14 21:23
 */
//验证码资源枚举
public enum VerificationSourceEnum {

    SLIDER("滑块"),
    RESOURCE("验证码源");

    private final String description;

    VerificationSourceEnum(String desc) {
        this.description = desc;
    }
}
