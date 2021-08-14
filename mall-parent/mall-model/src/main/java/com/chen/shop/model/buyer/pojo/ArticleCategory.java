package com.chen.shop.model.buyer.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName ArticleCategory
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/14 8:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleCategory {

    private Long id;

    private Integer level;

    private Long parentId;

    private Integer sort;

    private String articleCategoryName;

    private String type;

    private Boolean deleteFlag;

    private Date createTime;

    private Date updateTime;
}
