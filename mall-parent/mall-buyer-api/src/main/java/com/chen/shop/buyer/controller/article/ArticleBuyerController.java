package com.chen.shop.buyer.controller.article;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chen.shop.buyer.service.article.BuyerArticleService;
import com.chen.shop.common.vo.Result;
import com.chen.shop.model.buyer.params.ArticleSearchParams;
import com.chen.shop.model.buyer.vo.article.ArticleVO;
import io.swagger.annotations.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ArticleBuyerController
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/13 15:51
 */
@RestController
@Api(tags = "买家端，文章接口")
@RequestMapping("/article")
public class ArticleBuyerController {

    @Autowired
    private BuyerArticleService buyerArticleService;

    @GetMapping
    public Result<Page<ArticleVO>> getByPage(ArticleSearchParams articleSearchParams) {
        return Result.success(buyerArticleService.articlePage(articleSearchParams));
    }

}
