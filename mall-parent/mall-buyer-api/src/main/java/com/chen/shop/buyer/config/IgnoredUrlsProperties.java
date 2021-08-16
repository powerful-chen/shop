package com.chen.shop.buyer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName IgnoredUrlsProperties
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/16 11:40
 */
@Configuration
@ConfigurationProperties(prefix = "ignored")
@Data
public class IgnoredUrlsProperties {

    //忽略的权限选项
    private List<String> urls = new ArrayList<>();
}
