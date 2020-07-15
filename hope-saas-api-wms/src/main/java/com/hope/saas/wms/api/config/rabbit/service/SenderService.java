package com.hope.saas.wms.api.config.rabbit.service;

/**
 * @author Maduo
 * @date 2020/3/23 14:35
 */
public interface SenderService {

    /**
     * 发送
     *
     * @param queueName
     * @param data
     */
    void send(String queueName, Object data);
}
