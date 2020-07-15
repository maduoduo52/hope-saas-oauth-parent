package com.hope.saas.common.vo.wms.sys;

import com.hope.saas.common.vo.util.BaseVo;
import lombok.Data;


/**
 * 用户 - 职位 关联表
 *
 * @author Maduo
 * @date 2020-05-22 17:44:00
 */
@Data
public class SysUserPostVo extends BaseVo {
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 职位ID
     */
    private String postId;
}
