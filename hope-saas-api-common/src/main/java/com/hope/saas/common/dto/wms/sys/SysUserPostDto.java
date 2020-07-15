package com.hope.saas.common.dto.wms.sys;

import com.hope.saas.common.dto.util.BaseDTO;
import lombok.Data;


/**
 * 用户 - 职位 关联表
 *
 * @author Maduo
 * @date 2020-05-22 17:44:00
 */
@Data
public class SysUserPostDto extends BaseDTO {
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 职位ID
     */
    private String postId;
}
