package com.chen.shop.buyer.service.impl.goods;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chen.shop.buyer.mapper.GoodsSkuMapper;
import com.chen.shop.buyer.service.GoodsSkuService;
import com.chen.shop.model.buyer.pojo.es.EsGoodsIndex;
import com.chen.shop.model.buyer.pojo.goods.GoodsSku;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName GoodsSkuServiceImpl
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/19 21:32
 */
@DubboService(version = "1.0.0")
public class GoodsSkuServiceImpl implements GoodsSkuService {

    @Resource
    private GoodsSkuMapper goodsSkuMapper;
    @Autowired
    private ElasticsearchRestTemplate elasticsearchTemplate;

    @Override
    public void importES() {
        //导入es数据的时候，导入sku的数据，一般情况sku的数据 会有商品的数据属性，同时还有其余的属性（比如规格等信息）
        //一个商品 会对应多个sku
        LambdaQueryWrapper<GoodsSku> queryWrapper1 = new LambdaQueryWrapper<>();
        List<GoodsSku> goodsSkusList = goodsSkuMapper.selectList(queryWrapper1);
        for (GoodsSku goodsSku : goodsSkusList) {
            EsGoodsIndex esGoodsIndex = new EsGoodsIndex();
            BeanUtils.copyProperties(goodsSku, esGoodsIndex);

            esGoodsIndex.setId(goodsSku.getId().toString());
            esGoodsIndex.setGoodsId(goodsSku.getGoodsId().toString());
            esGoodsIndex.setPrice(goodsSku.getPrice().doubleValue());
            BigDecimal promotionPrice = goodsSku.getPromotionPrice();
            if (promotionPrice != null) {
                esGoodsIndex.setPromotionPrice(promotionPrice.doubleValue());
            }
            elasticsearchTemplate.save(esGoodsIndex);
        }
    }
}
