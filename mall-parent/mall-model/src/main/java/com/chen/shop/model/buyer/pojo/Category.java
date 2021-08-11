package com.chen.shop.model.buyer.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Category
 * @Description 商品分类
 * @Author xiaochen
 * @Date 2021/8/11 12:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    private Long id;

    private String name;

    private Long parentId;

    private Integer level;

    private Integer sortOrder;

    private Double commissionRate;

    private String image;

    private Boolean supportChannel;

    private Integer status;

    private Long createTime;

    private Long updateTime;
}
