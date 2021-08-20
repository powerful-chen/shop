package com.chen.shop.buyer.controller.goods;

import com.chen.shop.buyer.service.goods.GoodsSearchService;
import com.chen.shop.common.vo.Result;
import com.chen.shop.model.buyer.params.EsGoodsSearchParam;
import com.chen.shop.model.buyer.params.PageParams;
import com.chen.shop.model.buyer.vo.goods.GoodsPageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName GoodsBuyerController
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/13 11:13
 */
//热搜词
@Api(tags = "买家端，商品接口")
@RestController
@RequestMapping("/goods")
public class GoodsBuyerController {

    @Autowired
    private GoodsSearchService goodsSearchService;

    @ApiOperation(value = "获取搜索热词")
    @RequestMapping("/hot-words")
    public Result<List<String>> getGoodsHotWords(Integer start, Integer end) {
        List<String> hotWords = goodsSearchService.getHotWords(start, end);
        return Result.success(hotWords);
    }

    @ApiOperation(value = "从ES中获取商品信息")
    @GetMapping("/es")
    public Result<GoodsPageVO> getGoodsByPageFromEs(EsGoodsSearchParam goodsSearchParam, PageParams pageParams) {
        GoodsPageVO goodsPageVO = goodsSearchService.searchGoods(goodsSearchParam, pageParams);
        return Result.success(goodsPageVO);
    }

}
