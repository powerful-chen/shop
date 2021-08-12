package com.chen.shop.buyer.controller.pages;

import com.chen.shop.buyer.service.pages.PagesService;
import com.chen.shop.common.vo.Result;
import com.chen.shop.model.buyer.enums.PageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName PagesController
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/12 19:43
 */
@RestController
@RequestMapping("/pages")
public class PagesController {

    @Autowired
    private PagesService pagesService;

    @GetMapping("/index")
    public Result index(Integer clientType) {
        return pagesService.getPageIndexData(clientType, PageType.INDEX.getCode());
    }


}
