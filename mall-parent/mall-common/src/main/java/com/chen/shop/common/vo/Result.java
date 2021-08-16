package com.chen.shop.common.vo;

import com.chen.shop.common.enums.BusinessCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName Result
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/10 11:10
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable {

    /**
     * 成功标志
     */
    private boolean success;

    /**
     * 消息
     */
    private String message;

    /**
     * 返回代码
     */
    private Integer code;

    /**
     * 结果对象
     */
    private T result;

    public static <T> Result<T> success() {
        return new Result<>(true, "success", BusinessCodeEnum.DEFAULT_SUCCESS.getCode(), null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(true, "success", BusinessCodeEnum.DEFAULT_SUCCESS.getCode(), data);
    }

    public static <T> Result<T> fail(Integer code, String message) {
        return new Result<>(false, message, code, null);
    }

    public static <T> Result<T> fail() {
        return new Result<>(false, BusinessCodeEnum.DEFAULT_SYS_ERROR.getMsg(), BusinessCodeEnum.DEFAULT_SUCCESS.getCode(), null);
    }

}
