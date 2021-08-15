package com.chen.shop.model.buyer.vo.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName VerificationSourceVO
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/15 7:36
 */
@Data
public class VerificationSourceVO implements Serializable {

    private Long id;

    private String name;

    private String resource;

    private String type;
}
