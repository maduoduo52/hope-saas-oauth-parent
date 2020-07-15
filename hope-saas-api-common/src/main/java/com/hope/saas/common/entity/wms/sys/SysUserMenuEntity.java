package com.hope.saas.common.entity.wms.sys;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.hope.saas.common.entity.util.BaseEntity;
import com.hope.saas.common.table.wms.sys.SysUserMenuTable;
import lombok.Data;


/**
 * 用户 - 菜单 关联表
 *
 * @author Maduo
 * @date 2020-05-22 17:44:00
 */
@Data
@TableName("sys_user_menu")
public class SysUserMenuEntity extends BaseEntity {
    /**
     * 用户ID
     */
    @TableField(SysUserMenuTable.USER_ID)
    private Long userId;
    /**
     * 菜单ID
     */
    @TableField(SysUserMenuTable.MENU_ID)
    private Long menuId;
}
