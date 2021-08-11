package com.chen.shop.buyer.service.impl;

import com.chen.shop.buyer.service.ArticleService;
import org.apache.dubbo.config.annotation.DubboService;

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
    @Override
    public String findArticle() {
        return "test dubbo provider";
    }
}
