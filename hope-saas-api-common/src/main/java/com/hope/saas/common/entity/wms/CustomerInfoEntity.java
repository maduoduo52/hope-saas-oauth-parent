package com.hope.saas.common.entity.wms;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hope.saas.common.entity.util.BaseEntity;
import com.hope.saas.common.table.wms.CustomerInfoTable;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 用户信息表
 *
 * @author Maduo
 * @date 2020-03-20 14:09:44
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("customer_info")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerInfoEntity extends BaseEntity {

    private static final long serialVersionUID = 3253825931020678674L;
    /**
     * 租户id
     */
    @TableField(CustomerInfoTable.TENANT_ID)
    private String tenantId;

    /**
     * 货主编码
     */
    @TableField(CustomerInfoTable.SHIPPER_CODE)
    private String shipperCode;
    /**
     * 用户名称
     */
    @TableField(CustomerInfoTable.USER_NAME)
    private String userName;
    /**
     * 密码
     */
    @TableField(CustomerInfoTable.PASSWORD)
    private String password;
    /**
     * 盐值
     */
    @TableField(CustomerInfoTable.SALT)
    private String salt;
    /**
     * 公司名称
     */
    @TableField(CustomerInfoTable.COMPANY_NAME)
    private String companyName;
    /**
     * 公司地址
     */
    @TableField(CustomerInfoTable.COMPANY_ADDRESS)
    private String companyAddress;
    /**
     * 联系人名称
     */
    @TableField(CustomerInfoTable.PERSON_NAME)
    private String personName;
    /**
     * 联系人电话
     */
    @TableField(CustomerInfoTable.PERSON_PHONE)
    private String personPhone;
    /**
     * 用户加密解密key
     */
    @TableField(CustomerInfoTable.USER_KEY)
    private String userKey;
    /**
     * 入库反馈地址
     */
    @TableField(CustomerInfoTable.STORE_URL)
    private String storeUrl;
    /**
     * 出库反馈地址
     */
    @TableField(CustomerInfoTable.DELIVER_URL)
    private String deliverUrl;
    /**
     * 库存调整推送地址
     */
    @TableField(CustomerInfoTable.ADJUST_URL)
    private String adjustUrl;
    /**
     * 移库移位推送地址
     */
    @TableField(CustomerInfoTable.MOVE_URL)
    private String moveUrl;
    /**
     * 库存盘点推送地址
     */
    @TableField(CustomerInfoTable.INVENTORY_URL)
    private String inventoryUrl;

    /**
     * 备注
     */
    @TableField(CustomerInfoTable.BA_REMARK)
    private String baRemark;

    /**
     * 来源编码
     */
    @TableField(CustomerInfoTable.SOURCE_CODE)
    private String sourceCode;

    /**
     * 机构id
     */
    @TableField(CustomerInfoTable.ORG_ID)
    private Long orgId;

    @TableField(CustomerInfoTable.ADMIN)
    private Boolean admin;

    @TableField(CustomerInfoTable.STATUS)
    private Boolean status;
}
