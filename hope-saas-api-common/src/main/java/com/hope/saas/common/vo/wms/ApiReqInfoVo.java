package com.hope.saas.common.vo.wms;

import com.hope.saas.common.vo.util.BaseVo;
import lombok.Data;

import java.util.Date;


/**
 * api请求记录
 *
 * @author Maduo
 * @date 2020-03-20 14:09:44
 */
@Data
public class ApiReqInfoVo extends BaseVo {
    /**
     * 用户id
     */
    private Integer cusId;
    /**
     * 请求api
     */
    private String reqApi;
    /**
     * 请求参数
     */
    private String reqData;
    /**
     * 请求时间
     */
    private Date reqTime;
    /**
     * 响应参数
     */
    private String resData;
    /**
     * 请求ip地址
     */
    private String reqIp;
    /**
     * 备注
     */
    private String baRemark;
}
