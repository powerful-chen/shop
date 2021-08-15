package com.chen.shop.buyer.service;

import com.chen.shop.model.buyer.vo.common.slider.VerificationVO;

public interface VerificationSourceService {

    //查询数据库获取资源列表 得到资源列表和滑块列表
    VerificationVO findVerificationSource();
}
