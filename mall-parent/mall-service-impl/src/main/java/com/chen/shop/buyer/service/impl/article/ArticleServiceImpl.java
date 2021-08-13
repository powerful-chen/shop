package com.chen.shop.buyer.service.impl.article;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chen.shop.buyer.mapper.ArticleMapper;
import com.chen.shop.buyer.mapper.TestMapper;
import com.chen.shop.buyer.pojo.Test;
import com.chen.shop.buyer.service.ArticleService;
import com.chen.shop.model.buyer.params.ArticleSearchParams;
import com.chen.shop.model.buyer.pojo.Article;
import com.chen.shop.model.buyer.vo.article.ArticleVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ArticleServiceImpl
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/10 21:34
 */
//加dubbo注解
//发布当前的service服务到nacos上
//ip：port/接口名称/方法名称
//version便于 接口有不同的实现，或者版本升级之用
//interfaceClass 不加这个事务 无法使用
@DubboService(version = "1.0.0", interfaceClass = ArticleService.class)
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private TestMapper testMapper;

    @Override
    public String findArticle() {
        Test test = testMapper.selectById(1);
        return test.getUsername();
    }

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public Page<ArticleVO> articlePage(ArticleSearchParams articleSearchParams) {
        /**
         * 1、查询条件有多个
         * 2、查询条件进行判断，如果不为null才进行条件查询
         * 3、分页参数，分页查询
         * 4、从Article转换为ArticleVO，表的字段并不是都有用，用多少查多少
         * 5、copy copyList 做Article转换为ArticleVO的工作
         */
        if (!articleSearchParams.checkParams()) {
            //参数校验
            return new Page<>();
        }
        Page<Article> articlePage = new Page<>(articleSearchParams.getPageNumber(), articleSearchParams.getPageSize());
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(articleSearchParams.getCategoryId())) {
            queryWrapper.eq(Article::getCategoryId, articleSearchParams.getCategoryId());
        }
        if (StringUtils.isNotBlank(articleSearchParams.getTitle())) {
            //相当于 like aa%
            queryWrapper.likeRight(Article::getTitle, articleSearchParams.getTitle());
        }
        if (StringUtils.isNotBlank(articleSearchParams.getType())) {
            queryWrapper.eq(Article::getType, articleSearchParams.getType());
        }
        Page<Article> articlePage1 = articleMapper.selectPage(articlePage, queryWrapper);

        Page<ArticleVO> articleVOPage = new Page<>();
        BeanUtils.copyProperties(articlePage1, articleVOPage);
        List<Article> records = articlePage1.getRecords();
        List<ArticleVO> articleVOList = copyList(records);
        articleVOPage.setRecords(articleVOList);
        return articleVOPage;
    }

    private ArticleVO copy(Article article) {
        if (article == null) {
            return null;
        }
        ArticleVO articleVO = new ArticleVO();
        BeanUtils.copyProperties(article, articleVO);
        articleVO.setId(article.getId().toString());
        return articleVO;
    }

    private List<ArticleVO> copyList(List<Article> records) {
        List<ArticleVO> articleVOList = new ArrayList<>();

        for (Article article : records) {
            articleVOList.add(copy(article));
        }
        return articleVOList;
    }

}
