package com.hope.saas.common.dto.wms;

import com.hope.saas.common.dto.util.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotBlank;


/**
 * 用户信息表
 *
 * @author Maduo
 * @date 2020-03-20 14:09:44
 */
@Data
public class CustomerInfoDto extends BaseDTO {
    /**
     * 用户名称
     */
    @NotBlank(message = "userName不能为空")
    private String userName;
    /**
     * 密码
     */
    @NotBlank(message = "password不能为空")
    private String password;
    /**
     * 盐值
     */
    private String salt;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 公司地址
     */
    private String companyAddress;
    /**
     * 联系人名称
     */
    private String personName;
    /**
     * 联系人电话
     */
    private String personPhone;
    /**
     * 用户加密解密key
     */
    private String userKey;
    /**
     * 备注
     */
    private String baRemark;

    /**
     * 入库反馈地址
     */
    @NotBlank(message = "storeUrl不能为空")
    private String storeUrl;
    /**
     * 出库反馈地址
     */
    @NotBlank(message = "deliverUrl不能为空")
    private String deliverUrl;
    /**
     * 库存调整推送地址
     */
    @NotBlank(message = "adjustUrl不能为空")
    private String adjustUrl;
    /**
     * 移库移位推送地址
     */
    @NotBlank(message = "moveUrl不能为空")
    private String moveUrl;
    /**
     * 库存盘点推送地址
     */
    @NotBlank(message = "inventoryUrl不能为空")
    private String inventoryUrl;

    /**
     * 来源编码
     */
    @NotBlank(message = "sourceCode不能为空")
    private String sourceCode;
}
