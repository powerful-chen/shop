package com.chen.shop.buyer.service;

import com.chen.shop.common.vo.Result;
import com.chen.shop.model.buyer.vo.goods.GoodsDetailVO;

public interface GoodsSkuService {
    void importES();

    Result<GoodsDetailVO> getGoodsSkuDetail(String goodsId, String skuId);
}
