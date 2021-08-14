package com.chen.shop.buyer.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chen.shop.model.buyer.params.ArticleSearchParams;
import com.chen.shop.model.buyer.vo.article.ArticleCategoryVO;
import com.chen.shop.model.buyer.vo.article.ArticleVO;

import java.util.List;

/**
 * @ClassName ArticleService
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/10 21:32
 */
public interface ArticleService {
    String findArticle();

    //根据查询条件获取文章的分页信息
    Page<ArticleVO> articlePage(ArticleSearchParams articleSearchParams);

    //根据id获取文章详情
    ArticleVO findArticleById(Long id);

    //获取文章列表
    List<ArticleCategoryVO> findAllArticleCategory();
}
