package com.chen.shop.buyer.controller.article;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chen.shop.buyer.service.article.BuyerArticleService;
import com.chen.shop.common.vo.Result;
import com.chen.shop.model.buyer.params.ArticleSearchParams;
import com.chen.shop.model.buyer.vo.article.ArticleCategoryVO;
import com.chen.shop.model.buyer.vo.article.ArticleVO;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @ApiOperation(value = "通过id获取文章")
    @ApiImplicitParam(name = "id", value = "文章ID", required = true, paramType = "path")
    @GetMapping("/get/{id}")
    public Result<ArticleVO> get(@PathVariable("id") Long id) {
        return Result.success(buyerArticleService.customGet(id));
    }

    @ApiOperation(value = "获取文章分类列表")
    @GetMapping(value = "/articleCategory/list")
    public Result<List<ArticleCategoryVO>> getArticleCategoryList() {
        return Result.success(buyerArticleService.allChildren());
    }

}
