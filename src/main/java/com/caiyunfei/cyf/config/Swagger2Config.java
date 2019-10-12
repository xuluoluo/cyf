package com.caiyunfei.cyf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class Swagger2Config {
    @Bean
    public Docket createRestApi() {

        //swaggerui 设置添加请求头
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> parameterList = new ArrayList<>();
        tokenPar.name("token").description("用户token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        parameterList.add(tokenPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.shangxin.ustreet.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(parameterList);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("彩云啡API")
                .description("所有的旧接口都需要整改,新写的接口必须按照规范来")
                .termsOfServiceUrl("")
                .version("1.0")
                .contact(new Contact("chenjianjun","","1368574242@qq.com"))
                .build();
    }
}
