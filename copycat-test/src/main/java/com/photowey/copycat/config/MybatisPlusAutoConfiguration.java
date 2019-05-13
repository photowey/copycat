package com.photowey.copycat.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Properties;

/**
 * MybatisPlusAutoConfiguration
 *
 * @author WcJun
 * @date 2018/11/17
 */
@Configuration
// @EnableConfigurationProperties(DruidDataSourceProperties.class)
public class MybatisPlusAutoConfiguration {

    public static final String DATABASE_DIALECT_TYPE = "mysql";

    /**
     * 配置 config-plus 分页插件
     *
     * @return PaginationInterceptor
     */
    @Bean
    @ConditionalOnMissingBean(PaginationInterceptor.class)
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setDialectType(DATABASE_DIALECT_TYPE);
        return paginationInterceptor;
    }

    /**
     * 打印 sql
     */
    @Bean
    @Profile(value = "dev")
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        // 格式化sql语句
        Properties properties = new Properties();
        properties.setProperty("format", "true");
        performanceInterceptor.setProperties(properties);
        return performanceInterceptor;
    }

    /**
     * mp 逻辑删除
     *
     * @return ISqlInjector
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }
}