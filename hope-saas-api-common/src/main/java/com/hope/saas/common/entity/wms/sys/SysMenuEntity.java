package com.hope.saas.common.entity.wms.sys;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.hope.saas.common.entity.util.BaseEntity;
import com.hope.saas.common.table.wms.sys.SysMenuTable;
import lombok.Data;

import java.util.List;


/**
 * 菜单表
 *
 * @author Maduo
 * @date 2020-05-22 17:44:00
 */
@Data
@TableName("sys_menu")
public class SysMenuEntity extends BaseEntity {
    /**
     * 菜单名称
     */
    @TableField(SysMenuTable.NAME)
    private String name;
    /**
     * 类型  MENU 菜单  BUTTON 按钮（按钮包括权限）
     */
    @TableField(SysMenuTable.TYPE)
    private String type;
    /**
     * 状态  1 启用 0禁用
     */
    @TableField(SysMenuTable.STATUS)
    private Boolean status;
    /**
     * 图标
     */
    @TableField(SysMenuTable.ICON)
    private String icon;
    /**
     * 父节点
     */
    @TableField(SysMenuTable.PARENT)
    private Long parent;
    /**
     * 父节点集合
     */
    @TableField(SysMenuTable.PARENTS)
    private String parents;
    /**
     * 是否为根节点
     */
    @TableField(SysMenuTable.ROOT_FLAG)
    private Boolean rootFlag;
    /**
     * 排序
     */
    @TableField(SysMenuTable.ORDER_KEY)
    private Integer orderKey;
    /**
     * url 访问地址  主要为前端地址
     */
    @TableField(SysMenuTable.URL)
    private String url;
    /**
     * 权限地址  后台java接口访问地址
     */
    @TableField(SysMenuTable.AUTHORITY_URL)
    private String authorityUrl;
    /**
     * 权限按钮  此按钮IDS来控制 界面按钮或者字段显示与否
     */
    @TableField(SysMenuTable.AUTHORITY_BUTTON)
    private String authorityButton;

    @TableField(exist = false)
    private List<SysMenuEntity> sysMenuEntities;
}
