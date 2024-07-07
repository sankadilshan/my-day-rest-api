package com.sankadilshan.myday.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


@Slf4j
@Configuration
@ConfigurationProperties(prefix = "datasource")
public class DataSourceConfiguration {
    @Value("${datasource.db.username}")
    private String dbUsername;

    @Value("${datasource.db.password}")
    private String dbPassword;

    @Value("${datasource.db.url}")
    private String dbUrl;

    @Value("${datasource.db.driver-class-name}")
    private String driverClass;

    @Value("${datasource.query-timeout}")
    private int queryTimeOut;

    @Value("${datasource.function-timeout}")
    private int functionTimeOut;

    @Value("${datasource.max-active}")
    private int maxActive;

    @Value("${datasource.max-lifetime}")
    private int maxLifeTime;


    @Bean
    public HikariDataSource hikariDataSource(){
        log.info("database url {}", dbUrl);
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setJdbcUrl(dbUrl);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);
        dataSource.setMaximumPoolSize(maxActive);
        dataSource.setMaxLifetime(maxLifeTime);

        return  dataSource;
    }


    @Bean
    public NamedParameterJdbcTemplate namedTemplate() {
        JdbcTemplate jdbcTemplate =  new JdbcTemplate(hikariDataSource());
        jdbcTemplate.setQueryTimeout(queryTimeOut);
        return new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(hikariDataSource());
        jdbcTemplate.setQueryTimeout(queryTimeOut);
        return jdbcTemplate;
    }
}
