package com.hope.saas.wms.api.config.rabbit.config;

import com.hope.saas.wms.api.config.rabbit.RabbitConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author Maduo
 * @date 2020/3/23 14:29
 */
@Slf4j
@Configuration
public class RabbitMQFanoutConfig {

    /**
     * rabbit连接池
     *
     * @param host
     * @param port
     * @param username
     * @param password
     * @return
     */
    @Bean(value = "fanoutConnectionFactory")
    @Primary
    //标明是主链接
    ConnectionFactory connectionFactory(@Value("${spring.rabbitmq.host}") String host,
                                        @Value("${spring.rabbitmq.port}") Integer port,
                                        @Value("${spring.rabbitmq.virtualHost}") String virtualHost,
                                        @Value("${spring.rabbitmq.username}") String username,
                                        @Value("${spring.rabbitmq.password}") String password) {
        CachingConnectionFactory cf = new CachingConnectionFactory(host, port);
        cf.setUsername(username);
        cf.setPassword(password);
        cf.setVirtualHost(virtualHost);
        return cf;
    }

    /**
     * RabbitTemplate bean创建
     *
     * @param connectionFactory
     * @return
     */
    @Bean(name = "rabbitTemplate")
    public RabbitTemplate firstRabbitTemplate(@Qualifier("fanoutConnectionFactory") ConnectionFactory connectionFactory) {
        RabbitTemplate firstRabbitTemplate = new RabbitTemplate(connectionFactory);
        return firstRabbitTemplate;
    }

    /**
     * admin
     *
     * @param connectionFactory
     * @return
     */
    @Bean(value = "fanoutRabbitAdmin")
    RabbitAdmin rabbitAdmin(@Autowired @Qualifier("fanoutConnectionFactory") ConnectionFactory connectionFactory
//            , @Autowired HandleMessage handleMessage
    ) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        final String queueName = RabbitConstant.WMS_API_INTERFACE;
        //创建队列
        Queue queue = new Queue(queueName);
        rabbitAdmin.declareQueue(queue);
        //创建topic类型的交换机
        FanoutExchange exchange = new FanoutExchange(RabbitConstant.INTERFACE_EXCHANGE);
        rabbitAdmin.declareExchange(exchange);
        //交换机和队列绑定
        rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(exchange));

        //设置监听
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
//        MessageListenerAdapter adapter = new MessageListenerAdapter(handleMessage);
//        container.setMessageListener(adapter);
        container.setQueueNames(queueName);
        container.start();
        return rabbitAdmin;
    }
}
