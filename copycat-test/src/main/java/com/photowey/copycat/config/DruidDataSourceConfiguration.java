package com.photowey.copycat.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * DruidDataSourceAutoConfiguration
 *
 * @author WcJun
 * @date 2018/11/17
 */
@Configuration
public class DruidDataSourceConfiguration {
    public static final String DB_TYPE_MYSQL = "mysql";

    @Primary
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setDbType(DB_TYPE_MYSQL);
        return datasource;
    }
}