package com.chen.shop.buyer.service.impl.pages;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chen.shop.buyer.mapper.PageTemplateMapper;
import com.chen.shop.buyer.service.PageService;
import com.chen.shop.buyer.mapper.TemplateDetailMapper;
import com.chen.shop.common.vo.Result;
import com.chen.shop.model.buyer.enums.StatusEnum;
import com.chen.shop.model.buyer.pojo.PageTemplate;
import com.chen.shop.model.buyer.pojo.TemplateDetail;
import com.chen.shop.model.buyer.vo.pages.*;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName PageServiceImpl
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/12 20:29
 */
@DubboService(version = "1.0.0", interfaceClass = PageService.class)
public class PageServiceImpl implements PageService {

    @Autowired
    private PageTemplateMapper pageTemplateMapper;

    @Autowired
    private TemplateDetailMapper templateDetailMapper;

    @Override
    public Result findPageTemplate(Integer clientType, int pageType) {
        /**
         * 1、根据 clientType和pageType 获取模板信息 ms_page_template 模板id，模板名称
         * 2、根据模板id，获取模板详情信息，获取到的是List
         * 3、根据不同的模板类型，转换模板数据 为对应的实体类
         * 4、封装为map 进行返回即可
         */
        LambdaQueryWrapper<PageTemplate> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(PageTemplate::getClientType, clientType)
                .eq(PageTemplate::getPageType, pageType)
                .eq(PageTemplate::getStatus, StatusEnum.NORMAL.getCode());
        queryWrapper.last("limit 1");
        PageTemplate pageTemplate = pageTemplateMapper.selectOne(queryWrapper);

        if (pageTemplate == null) {
            return Result.fail(-999, "模板不存在");
        }
        Long id = pageTemplate.getId();
        LambdaQueryWrapper<TemplateDetail> detailQueryWrapper = new LambdaQueryWrapper<>();
        detailQueryWrapper.eq(TemplateDetail::getTemplateId, id).eq(TemplateDetail::getStatus, StatusEnum.NORMAL.getCode());
        List<TemplateDetail> templateDetails = templateDetailMapper.selectList(detailQueryWrapper);
        Map<String, Object> map = new LinkedHashMap<>();
        for (TemplateDetail templateDetail : templateDetails) {
            String templateData = templateDetail.getTemplateData();
            String templateType = templateDetail.getTemplateType();
            if ("topAdvert".equals(templateType)) {
                TopAdvert topAdvert = JSON.parseObject(templateData, TopAdvert.class);
                map.put(templateDetail.getTemplateType(), topAdvert);
            }
            if ("navBar".equals(templateType)) {
                NavBar navBar = JSON.parseObject(templateData, NavBar.class);
                map.put(templateDetail.getTemplateType(), navBar);
            }
            if ("carousel".equals(templateType)) {
                Carousel carousel = JSON.parseObject(templateData, Carousel.class);
                map.put(templateDetail.getTemplateType(), carousel);
            }
            if ("discountAdvert".equals(templateType)) {
                DiscountAdvert discountAdvert = JSON.parseObject(templateData, DiscountAdvert.class);
                map.put(templateDetail.getTemplateType(), discountAdvert);
            }
            if ("recommend".equals(templateType)) {
                PageRecommend pageRecommend = JSON.parseObject(templateData, PageRecommend.class);
                map.put(templateDetail.getTemplateType(), pageRecommend);
            }
            if ("newGoodsSort".equals(templateType)) {
                NewGoodsSort newGoodsSort = JSON.parseObject(templateData, NewGoodsSort.class);
                map.put(templateDetail.getTemplateType(), newGoodsSort);
            }
            if ("firstAdvert".equals(templateType)) {
                FirstAdvert firstAdvert = JSON.parseObject(templateData, FirstAdvert.class);
                map.put(templateDetail.getTemplateType(), firstAdvert);
            }
            if ("notEnough".equals(templateType)) {
                NotEnough notEnough = JSON.parseObject(templateData, NotEnough.class);
                map.put(templateDetail.getTemplateType(), notEnough);
            }
        }
        return Result.success(map);
    }
}
