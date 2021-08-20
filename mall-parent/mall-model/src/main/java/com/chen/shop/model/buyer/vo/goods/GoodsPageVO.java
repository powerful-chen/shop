package com.chen.shop.model.buyer.vo.goods;

import com.chen.shop.model.buyer.pojo.es.EsGoodsIndex;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName GoodsPageVO
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/20 9:25
 */
@Data
public class GoodsPageVO implements Serializable {

    private Long totalElements;

    private List<EsGoodsIndex> content;
}
