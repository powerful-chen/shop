package com.chen.shop.model.buyer.pojo;

import lombok.Data;

/**
 * @ClassName VerificationSource
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/14 21:21
 */
@Data
public class VerificationSource {

    private Long id;

    private String name;

    private String resource;

    //资源 滑块
    private String type;

    private Boolean deleteFlag;
}
