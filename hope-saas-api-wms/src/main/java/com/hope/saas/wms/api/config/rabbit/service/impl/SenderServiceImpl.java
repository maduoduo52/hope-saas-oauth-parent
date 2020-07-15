package com.hope.saas.wms.api.config.rabbit.service.impl;

import com.alibaba.fastjson.JSON;
import com.hope.saas.wms.api.config.rabbit.RabbitConstant;
import com.hope.saas.wms.api.config.rabbit.pojo.RedundancyDto;
import com.hope.saas.wms.api.config.rabbit.service.SenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author Maduo
 * @date 2020/3/23 14:35
 */
@Slf4j
@Service
@Component
public class SenderServiceImpl implements SenderService {

    @Autowired
    @Qualifier("fanoutConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送
     *
     * @param queueName
     * @param data
     */
    @Override
    public synchronized void send(String queueName, Object data) {

        if (rabbitTemplate == null) {
            rabbitTemplate = new RabbitTemplate(connectionFactory);
        }

        RedundancyDto redundancyDto = new RedundancyDto();
        redundancyDto.setQueueName(queueName);
        if (data instanceof Object) {
            redundancyDto.setData(JSON.toJSONString(data));
        } else if (data instanceof String) {
            redundancyDto.setData((String) data);
        }
        String dataDto = JSON.toJSONString(redundancyDto);
        log.info("发送广播消息:{},{}", queueName, dataDto);
        rabbitTemplate.convertAndSend(RabbitConstant.INTERFACE_EXCHANGE, queueName, dataDto);
    }
}
