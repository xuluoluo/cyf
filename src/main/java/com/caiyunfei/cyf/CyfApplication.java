package com.caiyunfei.cyf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@MapperScan("com.caiyunfei.cyf.dao")
@ServletComponentScan
public class CyfApplication {

    public static void main(String[] args) {
        SpringApplication.run(CyfApplication.class, args);
    }

}
