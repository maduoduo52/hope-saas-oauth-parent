package com.hope.saas.common.vo.wms.sys;

import com.hope.saas.common.vo.util.BaseVo;
import lombok.Data;


/**
 * 用户 - 菜单 关联表
 *
 * @author Maduo
 * @date 2020-05-22 17:44:00
 */
@Data
public class SysUserMenuVo extends BaseVo {
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 菜单ID
     */
    private String menuId;
}
