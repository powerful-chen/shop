package com.chen.shop.buyer.service.goods;

import com.chen.shop.buyer.service.GoodsService;
import com.chen.shop.model.buyer.params.EsGoodsSearchParam;
import com.chen.shop.model.buyer.params.PageParams;
import com.chen.shop.model.buyer.vo.goods.GoodsPageVO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @ClassName GoodsSearchService
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/13 11:21
 */
@Service
public class GoodsSearchService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @DubboReference(version = "1.0.0")
    private GoodsService goodsService;

    public static String HOT_WORDS_REDIS_KEY = "goods_hot_words";

    public List<String> getHotWords(Integer start, Integer end) {
        /**
         * 1、redis的zset 数据结构
         * 2、要按照score从大到小排
         * 3、redis数据来源，先用测试数据，后端做搜索的时候，redis数据通过搜索接口放入
         */
        start = (start - 1) * end;
        end = start + end;
        Set<ZSetOperations.TypedTuple<String>> typedTuples = redisTemplate.opsForZSet().reverseRangeWithScores(HOT_WORDS_REDIS_KEY, start, end);
        List<String> hotWords = new ArrayList<>();
        if (typedTuples == null) {
            return hotWords;
        }
        for (ZSetOperations.TypedTuple<String> typedTuple : typedTuples) {
            hotWords.add(typedTuple.getValue());
        }
        return hotWords;
    }

    public GoodsPageVO searchGoods(EsGoodsSearchParam goodsSearchParam, PageParams pageParams) {
        return goodsService.searchGoods(goodsSearchParam, pageParams);
    }
}
