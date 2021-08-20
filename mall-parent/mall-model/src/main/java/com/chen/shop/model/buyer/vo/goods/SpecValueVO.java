package com.chen.shop.model.buyer.vo.goods;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName SpecValueVO
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/20 15:36
 */
//规格值
@Data
public class SpecValueVO implements Serializable {

    private String specName;

    private String specValue;

    private Integer specType;
    /**
     * 规格图片
     */
    private List<SpecImages> specImage;

}
