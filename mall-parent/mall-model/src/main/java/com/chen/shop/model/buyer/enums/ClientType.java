package com.chen.shop.model.buyer.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ClientType
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/12 20:01
 */
public enum ClientType {
    PC(0, "pc"),
    H5(1, "h5"),
    WAP(2, "wap");

    private int code;
    private String message;

    private static final Map<Integer, ClientType> CODE_MAP = new HashMap<>(3);

    static {
        for (ClientType clientType : values()) {
            CODE_MAP.put(clientType.getCode(), clientType);
        }
    }

    ClientType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static ClientType codeOf(int code) {
        return CODE_MAP.get(code);
    }
}
