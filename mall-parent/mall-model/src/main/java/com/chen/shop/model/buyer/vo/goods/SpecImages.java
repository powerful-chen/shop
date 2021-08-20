package com.chen.shop.model.buyer.vo.goods;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName SpecImages
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/20 15:36
 */
@Data
public class SpecImages implements Serializable {

    private String url;

    private String name;

    private String status;
}
