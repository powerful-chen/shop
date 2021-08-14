package com.chen.shop.model.buyer.vo.article;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @ClassName ArticleCategoryVO
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/14 9:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleCategoryVO implements Serializable {

    private String id;

    private Integer level;

    private String parentId;

    private Integer sort;

    private String articleCategoryName;

    private String type;

    @ApiModelProperty(value = "子菜单")
    private List<ArticleCategoryVO> children = new ArrayList<>();

    public List<ArticleCategoryVO> getChildren() {
        if (children != null) {
            children.sort(new Comparator<ArticleCategoryVO>() {
                @Override
                public int compare(ArticleCategoryVO o1, ArticleCategoryVO o2) {
                    return o1.getSort().compareTo(o2.getSort());
                }
            });
            return children;
        }
        return null;
    }
}
