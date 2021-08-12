package com.chen.shop.buyer.service;

import com.chen.shop.common.vo.Result;

public interface PageService {
    //首页装修模板
    Result findPageTemplate(Integer clientType, int pageType);
}
