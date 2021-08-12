package com.chen.shop.model.buyer.enums;

/**
 * @ClassName StatusEnum
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/12 20:15
 */
public enum StatusEnum {

    NORMAL(0, "正常"),
    DELETE(1, "删除");

    private int code;
    private String message;

    StatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
