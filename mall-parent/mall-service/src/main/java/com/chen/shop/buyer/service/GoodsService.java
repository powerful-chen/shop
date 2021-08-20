package com.chen.shop.buyer.service;

import com.chen.shop.model.buyer.params.EsGoodsSearchParam;
import com.chen.shop.model.buyer.params.PageParams;
import com.chen.shop.model.buyer.vo.goods.GoodsPageVO;

public interface GoodsService {
    /**
     * 根据搜索条件，进行搜索
     * @param goodsSearchParam
     * @param pageParams
     * @return
     */
    GoodsPageVO searchGoods(EsGoodsSearchParam goodsSearchParam, PageParams pageParams);
}
