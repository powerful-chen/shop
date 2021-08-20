package com.chen.shop.buyer.service;

import com.chen.shop.model.buyer.vo.CategoryVO;

import java.util.List;

public interface CategoryService {
    /**
     * 根据父id获取对应的商品分类列表
     *
     * @param parentId
     * @return
     */
    List<CategoryVO> findCategoryTree(Long parentId);

    List<String> getCategoryNameByIds(List<String> idList);
}
