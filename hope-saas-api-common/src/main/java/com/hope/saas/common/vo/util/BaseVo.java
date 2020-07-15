package com.hope.saas.common.vo.util;

import lombok.Data;

import java.util.Date;

/**
 * @author Maduo
 * @date 2020/3/20 13:46
 */
@Data
public class BaseVo {

    private String tenantId;

    private String shipperCode;
    /**
     * 添加时间
     */
    protected Date addTime;

    /**
     * 修改时间
     */
    protected Date updateTime;

    /**
     * 版本号
     */
    protected Integer version;
}
