package com.chen.shop.buyer.service.pages;

import com.chen.shop.buyer.service.PageService;
import com.chen.shop.common.vo.Result;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

/**
 * @ClassName PagesService
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/12 19:48
 */
@Service
public class PagesService {

    @DubboReference(version = "1.0.0")
    private PageService pageService;

    public Result getPageIndexData(Integer clientType, int pageType) {
        return pageService.findPageTemplate(clientType, pageType);
    }
}
