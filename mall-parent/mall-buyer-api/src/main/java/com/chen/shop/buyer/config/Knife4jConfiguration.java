package com.chen.shop.buyer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @ClassName Knife4jConfiguration
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/10 17:31
 */

@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {
    //配置Swagger
    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("小陈商城API列表")
                        .description("小陈商城 rest API列表")
                        .termsOfServiceUrl("http://handsome-tao.qqly.xyz/")
                        .contact(new Contact("小陈", "", "xiaochen@qq.com"))
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("3.X版本")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.chen.shop.buyer.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}
