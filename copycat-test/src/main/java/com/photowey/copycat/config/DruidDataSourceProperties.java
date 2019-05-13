package com.photowey.copycat.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * DruidDataSourceAutoConfiguration
 *
 * @author WcJun
 * @date 2018/11/17
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@Configuration
// @ConfigurationProperties(prefix = "spring.datasource", ignoreUnknownFields = true)
public class DruidDataSourceProperties {

    private String url;

    private String username;

    private String password;

    private String driverClassName;

    private int initialSize;

    private int minIdle;

    private int maxActive;

    private int maxWait;

    private int timeBetweenEvictionRunsMillis;

    private int minEvictableIdleTimeMillis;

    private String validationQuery;

    private boolean testWhileIdle;

    private boolean testOnBorrow;

    private boolean testOnReturn;

    private boolean poolPreparedStatements;

    private int maxPoolPreparedStatementPerConnectionSize;

    private int maxOpenPreparedStatements;

    private String filters;

    private String connectionProperties;

}