package com.caiyunfei.cyf.config;

import com.caiyunfei.cyf.common.ComParameter;
import com.caiyunfei.cyf.util.SpringUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description: SystemConfig
 * date: 2019/9/18 10:11
 * author: 徐家斌
 * version: 1.0
 */
@Configuration
public class SystemConfig {
    @Bean
    public ComParameter getSystemParameter(){
        return new ComParameter();
    }
    @Bean
    public SpringUtil getSpringUtil(){
        return new SpringUtil();
    }
}
