package com.hope.saas.wms.api.config.rabbit.config;

import com.hope.saas.wms.api.config.rabbit.RabbitConstant;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Maduo
 * @date 2020/3/23 16:53
 */
@Configuration
public class RabbitQueue {

    @Bean
    public Queue interfaceQueue() {
        return new Queue(RabbitConstant.WMS_API_INTERFACE);
    }
}
