package com.chen.shop.model.buyer.vo.article;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName ArticleVO
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/13 16:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleVO implements Serializable {

    @ApiModelProperty(value = "文章ID")
    private String id;

    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "文章内容")
    private String content;

    @ApiModelProperty(value = "分类名称")
    private String articleCategoryName;

    @ApiModelProperty(value = "文章排序")
    private Integer sort;

    @ApiModelProperty(value = "状态，allowableValues = OPEN,CLOSE")
    private Boolean openStatus;
}
