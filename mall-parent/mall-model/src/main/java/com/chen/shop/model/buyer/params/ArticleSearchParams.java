package com.chen.shop.model.buyer.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ArticleSearchParams
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/13 16:04
 */
@Data
public class ArticleSearchParams extends PageParams implements Serializable {

    @ApiModelProperty(value = "分类ID")
    private String categoryId;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "分类类型")
    private String type;

}
