package com.hope.saas.wms.api.config.rabbit.config;

import com.alibaba.fastjson.JSONObject;
import com.hope.saas.wms.api.config.rabbit.RabbitConstant;
import com.hope.saas.wms.api.config.rabbit.pojo.RedundancyDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Maduo
 * @date 2020/3/23 17:08
 */
@Component
@Slf4j
public class HandleMessage {

    /**
     * 接受消息  消息处理器
     *
     * @param message
     */
    public void handleMessage(String message) {
        log.info("接收到广播消息:{}", message);
        RedundancyDto redundancyDto = JSONObject.parseObject(message, RedundancyDto.class);
        switch (redundancyDto.getQueueName()) {
            case RabbitConstant.WMS_API_INTERFACE:
                break;
            default:

        }
    }
}
