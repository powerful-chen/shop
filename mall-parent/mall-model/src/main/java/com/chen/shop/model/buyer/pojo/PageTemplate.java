package com.chen.shop.model.buyer.pojo;

import lombok.Data;

/**
 * @ClassName PageTemplate
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/12 9:39
 */
@Data
public class PageTemplate {

    private Long id;

    private String name;

    private Integer clientType;

    private Integer pageType;

    private Integer openStatus;

    private Integer status;

    private Long createTime;

    private Long updateTime;
}
