package com.hope.saas.wms.api.config.rabbit.pojo;

import lombok.Data;

/**
 * @author Maduo
 * @date 2020/3/23 14:28
 */
@Data
public class RedundancyDto {

    /**
     * 广播类型
     */
    private String queueName;

    /**
     * 数据
     */
    private String data;
}
