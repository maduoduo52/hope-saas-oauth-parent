package com.hope.saas.wms.api.config.start;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Maduo
 * @date 2020/4/14 17:20
 */
@Component
@Order(value = 1)   //数值越小，优先级越高
public class StartConfig implements ApplicationRunner {

    @Value("${spring.profiles.active}")
    private String active;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        StartConstant.active = active;
    }
}
