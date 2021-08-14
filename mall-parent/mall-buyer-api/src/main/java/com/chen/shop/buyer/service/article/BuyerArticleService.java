package com.chen.shop.buyer.service.article;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chen.shop.buyer.service.ArticleService;
import com.chen.shop.model.buyer.params.ArticleSearchParams;
import com.chen.shop.model.buyer.vo.article.ArticleCategoryVO;
import com.chen.shop.model.buyer.vo.article.ArticleVO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName BuyerArticleService
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/13 16:16
 */
//文章列表
@Service
public class BuyerArticleService {

    @DubboReference(version = "1.0.0")
    private ArticleService articleService;

    public Page<ArticleVO> articlePage(ArticleSearchParams articleSearchParams) {
        return articleService.articlePage(articleSearchParams);
    }

    public ArticleVO customGet(Long id) {
        return articleService.findArticleById(id);
    }

    public List<ArticleCategoryVO> allChildren() {
        return articleService.findAllArticleCategory();
    }
}
