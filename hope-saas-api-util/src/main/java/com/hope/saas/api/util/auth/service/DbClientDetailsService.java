package com.hope.saas.api.util.auth.service;

import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

/**
 * DbClientDetailsService
 *
 * @author Yangqi.Pang
 * @date 2020-04-09 22:10
 */
public class DbClientDetailsService {

    private DataSource dataSource;

    public DbClientDetailsService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public ClientDetailsService clientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }
}
