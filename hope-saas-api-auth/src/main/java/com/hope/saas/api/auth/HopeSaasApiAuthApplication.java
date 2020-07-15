package com.hope.saas.api.auth;

import com.hope.saas.api.util.auth.annotation.EnableAuthJwtTokenStore;
import com.hope.saas.api.util.auth.annotation.EnableDBClientDetailsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * HopeSaasApiOauthApplication
 *
 * @author Yangqi.Pang
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableDBClientDetailsService
@EnableAuthJwtTokenStore
public class HopeSaasApiAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(HopeSaasApiAuthApplication.class, args);
    }

}
