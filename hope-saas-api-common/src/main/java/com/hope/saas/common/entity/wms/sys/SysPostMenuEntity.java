package com.hope.saas.common.entity.wms.sys;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.hope.saas.common.entity.util.BaseEntity;
import com.hope.saas.common.table.wms.sys.SysPostMenuTable;
import lombok.Data;


/**
 * 职位 - 菜单 关联表
 *
 * @author Maduo
 * @date 2020-05-22 17:44:00
 */
@Data
@TableName("sys_post_menu")
public class SysPostMenuEntity extends BaseEntity {
    /**
     *
     */
    @TableField(SysPostMenuTable.MENU_ID)
    private Long menuId;
    /**
     *
     */
    @TableField(SysPostMenuTable.POST_ID)
    private Long postId;
}
