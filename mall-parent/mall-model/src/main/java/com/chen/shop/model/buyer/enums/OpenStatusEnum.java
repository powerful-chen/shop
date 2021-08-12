package com.chen.shop.model.buyer.enums;

/**
 * @ClassName OpenStatusEnum
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/12 20:13
 */
public enum OpenStatusEnum {

    OPEN(0, "open"),
    CLOSE(1, "close");

    private int code;
    private String message;

    OpenStatusEnum(int code, String message) {
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
