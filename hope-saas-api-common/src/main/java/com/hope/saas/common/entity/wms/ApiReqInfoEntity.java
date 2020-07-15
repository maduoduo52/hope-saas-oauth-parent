package com.hope.saas.common.entity.wms;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.hope.saas.common.entity.util.BaseEntity;
import com.hope.saas.common.table.wms.ApiReqInfoTable;
import lombok.Data;

import java.util.Date;


/**
 * api请求记录
 *
 * @author Maduo
 * @date 2020-03-20 14:09:44
 */
@Data
@TableName("api_req_info")
public class ApiReqInfoEntity extends BaseEntity {
    /**
     * 用户id
     */
    @TableField(ApiReqInfoTable.CUS_ID)
    private Long cusId;
    /**
     * 请求api
     */
    @TableField(ApiReqInfoTable.REQ_API)
    private String reqApi;
    /**
     * 请求参数
     */
    @TableField(ApiReqInfoTable.REQ_DATA)
    private String reqData;
    /**
     * 请求时间
     */
    @TableField(ApiReqInfoTable.REQ_TIME)
    private Date reqTime;
    /**
     * 响应参数
     */
    @TableField(ApiReqInfoTable.RES_DATA)
    private String resData;
    /**
     * 请求ip地址
     */
    @TableField(ApiReqInfoTable.REQ_IP)
    private String reqIp;
    /**
     * 备注
     */
    @TableField(ApiReqInfoTable.BA_REMARK)
    private String baRemark;
}
