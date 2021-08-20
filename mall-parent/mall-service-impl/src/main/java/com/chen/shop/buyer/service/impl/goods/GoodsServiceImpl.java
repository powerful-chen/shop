package com.chen.shop.buyer.service.impl.goods;

import com.chen.shop.buyer.service.GoodsService;
import com.chen.shop.model.buyer.params.EsGoodsSearchParam;
import com.chen.shop.model.buyer.params.PageParams;
import com.chen.shop.model.buyer.pojo.es.EsGoodsIndex;
import com.chen.shop.model.buyer.vo.goods.GoodsPageVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.*;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName GoodsServiceImpl
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/20 9:40
 */
@DubboService(version = "1.0.0", interfaceClass = GoodsService.class)
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchTemplate;

    @Override
    public GoodsPageVO searchGoods(EsGoodsSearchParam goodsSearchParam, PageParams pageParams) {
        /**
         * 1、拿到搜索 本质上很多，首页的搜索，假定搜索条件只有一个keyword
         * 2、es中查询商品名称是否匹配，must（一定匹配）
         * 3、卖点 也需要进行匹配 should（不一定匹配）
         */
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (StringUtils.isNotBlank(goodsSearchParam.getKeyword())){
            MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("goodsName", goodsSearchParam.getKeyword());
            MatchQueryBuilder matchQueryBuilder1 = QueryBuilders.matchQuery("sellingPoint", goodsSearchParam.getKeyword());
            boolQueryBuilder.must(matchQueryBuilder);
            boolQueryBuilder.should(matchQueryBuilder1);
        }
        PageRequest pageRequest = PageRequest.of(pageParams.getPageNumber() > 0 ? pageParams.getPageNumber() - 1 : pageParams.getPageNumber(), pageParams.getPageSize());
        NativeSearchQuery query =
                new NativeSearchQueryBuilder()
                        .withQuery(boolQueryBuilder)
                        .withPageable(pageRequest)
                        .build();
        SearchHits<EsGoodsIndex> search = elasticsearchTemplate.search(query, EsGoodsIndex.class);
        SearchPage<EsGoodsIndex> searchHits = SearchHitSupport.searchPageFor(search, pageRequest);

        GoodsPageVO goodsPageVo = new GoodsPageVO();
        goodsPageVo.setTotalElements(searchHits.getTotalElements());
        List<EsGoodsIndex> collect = searchHits.getContent().stream().map(SearchHit::getContent).collect(Collectors.toList());
        goodsPageVo.setContent(collect);
        return goodsPageVo;

    }
}
