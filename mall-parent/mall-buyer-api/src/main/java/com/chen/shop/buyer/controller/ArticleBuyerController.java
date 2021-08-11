package com.chen.shop.buyer.controller;

import com.chen.shop.buyer.service.ArticleService;
import com.chen.shop.common.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ArticleBuyerController
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/10 17:42
 */
@RestController
@Api(tags = "买家端，文章接口")
@RequestMapping("/article")
public class ArticleBuyerController {

    //1、问nacos 是否有此服务，nacos回复有则返回调用链接
    //2、进行rpc调用
    //3、传参以及解析返回的参数（序列化与反序列化的过程）
    @DubboReference(version = "1.0.0")
    private ArticleService articleService;

    @ApiOperation(value = "分页获取")
    @GetMapping
    public Result getByPage() {
        return Result.success(articleService.findArticle());
    }
}
