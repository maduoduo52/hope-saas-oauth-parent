package com.hope.saas.common.vo.wms.sys;

import com.hope.saas.common.vo.util.BaseVo;
import lombok.Data;


/**
 * 菜单表
 *
 * @author Maduo
 * @date 2020-05-22 17:44:00
 */
@Data
public class SysMenuVo extends BaseVo {
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 类型  MENU 菜单  BUTTON 按钮（按钮包括权限）
     */
    private String type;
    /**
     * 状态  1 启用 0禁用
     */
    private Integer status;
    /**
     * 图标
     */
    private String icon;
    /**
     * 父节点
     */
    private Long parent;
    /**
     * 父节点集合
     */
    private String parents;
    /**
     * 是否为根节点
     */
    private Integer rootFlag;
    /**
     * 排序
     */
    private Integer orderKey;
    /**
     * url 访问地址  主要为前端地址
     */
    private String url;
    /**
     * 权限地址  后台java接口访问地址
     */
    private String authorityUrl;
    /**
     * 权限按钮  此按钮IDS来控制 界面按钮或者字段显示与否
     */
    private String authorityButton;
}
