package com.hope.saas.wms.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Yangqi.Pang
 * @date 2020-04-01 09:55
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().sameOrigin()
                .httpStrictTransportSecurity().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 忽略匹配项
        web.ignoring().antMatchers("/templates/**");
    }

}
