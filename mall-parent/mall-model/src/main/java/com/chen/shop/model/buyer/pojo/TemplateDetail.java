package com.chen.shop.model.buyer.pojo;

import lombok.Data;

/**
 * @ClassName TemplateDetail
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/12 9:42
 */
@Data
public class TemplateDetail {

    private Long id;

    private Long templateId;

    private String templateType;

    private String templateData;

    private Integer status;

    private Long createTime;
}
