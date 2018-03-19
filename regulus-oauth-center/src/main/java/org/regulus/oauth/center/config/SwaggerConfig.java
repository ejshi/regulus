/**
 * Project Name:regulus-account-consumer
 * File Name:SwaggerConfig.java
 * Package Name:org.regulus.account.consumer.config
 * Date:2018年1月26日下午5:00:58
 * Copyright (c) 2018, http://www.richinfo.cn All Rights Reserved.
 *
*/

package org.regulus.oauth.center.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName: SwaggerConfig  
 * @Description: swagger配置
 * @author ejshi  
 * @date 2018年1月31日 下午7:53:38  
 */
@SpringBootConfiguration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.regulus.oauth.center.controller"))
                .paths(PathSelectors.any())
//                .paths(Predicates.or(PathSelectors.regex("/[api|demo]/.*")))//过滤的接口
                .build();
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("Api Documentation")
                .description("Api Documentation")
                .version("1.0.0")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .contact(new Contact("shijun", "https://swagger.io/", "gsshijun@126.com"))
                .build();
    }
}

