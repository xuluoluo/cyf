package com.caiyunfei.cyf.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * description: DruidConfiguration
 * date: 2019/10/10 11:07
 * author: 徐家斌
 * version: 1.0
 */
@Configuration
public class DruidConfig {


    private static final Logger log = LoggerFactory.getLogger(DruidConfig.class);

    /**必须配置数据源，不然无法获取到sql监控，与sql防火墙监控*/
    @Bean(name = "default_databaseSource")
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource druidDataSource() {
        DruidDataSource druid = new DruidDataSource();
        try {
            druid.setFilters("stat,wall");
        } catch (SQLException ignored) {
        }
        return druid;
    }

}
