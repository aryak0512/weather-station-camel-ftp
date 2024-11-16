package com.example.weather.beans;

import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author aryak
 * @apiNote Configuration of data sources, DBCP settings & templates for PSMW
 * done here
 * @since 15th Feb 2024
 **/
@Configuration
public class DatabaseConfig {

    @Value("${aws.db.url}")
    private String url;

    @Value("${aws.db.username}")
    private String username;

    @Value("${aws.db.password}")
    private String password;

    @Value("${aws.db.driver}")
    private String driver;

    @Bean(name = "awsDatasource")
    public DataSource dataSource() {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        // Configure the connection pool
        dataSource.setInitialSize(5);       // Initial number of connections
        dataSource.setMaxTotal(10);          // Maximum number of connections
        dataSource.setMaxIdle(1);           // Maximum number of idle connections
        dataSource.setMinIdle(1);           // Minimum number of idle connections
        dataSource.setMaxWaitMillis(10000); // Maximum wait time for a connection

        // Close connections after being idle for a certain period
        dataSource.setMinEvictableIdleTimeMillis(60000); // 1 minute
        dataSource.setTimeBetweenEvictionRunsMillis(30000); // 30 seconds

        // Validate connections before use
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setTestOnBorrow(true);
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnReturn(false);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

}
