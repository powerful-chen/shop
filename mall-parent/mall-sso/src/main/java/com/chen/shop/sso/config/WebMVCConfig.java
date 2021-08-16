package com.chen.shop.sso.config;

import com.chen.shop.sso.handler.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName WebMVCConfig
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/10 17:27
 */
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    @Value("${buyer.url}")
    private String buyerUrl;

    @Autowired
    private RequestInterceptor requestInterceptor;

    //配置跨域
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins(buyerUrl);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestInterceptor);
    }
}
