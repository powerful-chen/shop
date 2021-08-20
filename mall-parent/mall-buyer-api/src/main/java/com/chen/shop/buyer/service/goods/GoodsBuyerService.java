package com.chen.shop.buyer.service.goods;

import com.chen.shop.buyer.service.GoodsSkuService;
import com.chen.shop.common.vo.Result;
import com.chen.shop.model.buyer.vo.goods.GoodsDetailVO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

/**
 * @ClassName GoodsBuyerService
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/20 20:14
 */
@Service
public class GoodsBuyerService {

    @DubboReference(version = "1.0.0")
    private GoodsSkuService goodsSkuService;

    public Result<GoodsDetailVO> getGoodsSkuDetail(String goodsId, String skuId) {
        return goodsSkuService.getGoodsSkuDetail(goodsId, skuId);
    }
}
