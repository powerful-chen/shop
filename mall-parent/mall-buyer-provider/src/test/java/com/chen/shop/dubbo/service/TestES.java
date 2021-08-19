package com.chen.shop.dubbo.service;

import com.chen.shop.buyer.service.GoodsSkuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName TestES
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/19 22:28
 */
@SpringBootTest
public class TestES {

    @Autowired
    private GoodsSkuService goodsSkuService;

    @Test
    public void testImportES() {
        goodsSkuService.importES();
    }
}
