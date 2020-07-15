package com.hope.saas.common.vo.wms;

import com.hope.saas.common.vo.util.BaseVo;
import lombok.Data;


/**
 * 用户信息表
 *
 * @author Maduo
 * @date 2020-03-20 14:09:44
 */
@Data
public class CustomerInfoVo extends BaseVo {
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 密码
     */
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
}
