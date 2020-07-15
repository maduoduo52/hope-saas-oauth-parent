package com.hope.saas.common.entity.wms.sys;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.hope.saas.common.entity.util.BaseEntity;
import com.hope.saas.common.table.wms.sys.SysOrgTable;
import lombok.Data;


/**
 * 组织机构表
 *
 * @author Maduo
 * @date 2020-05-22 17:44:00
 */
@Data
@TableName("sys_org")
public class SysOrgEntity extends BaseEntity {
    /**
     * 菜单名称
     */
    @TableField(SysOrgTable.NAME)
    private String name;
    /**
     * company 公司 department 部门
     */
    @TableField(SysOrgTable.TYPE)
    private String type;
    /**
     * 状态  1 启用 0禁用
     */
    @TableField(SysOrgTable.STATUS)
    private Boolean status;
    /**
     * 父节点
     */
    @TableField(SysOrgTable.PARENT)
    private Long parent;
    /**
     * 父节点集合
     */
    @TableField(SysOrgTable.PARENTS)
    private String parents;
    /**
     * 是否为根节点
     */
    @TableField(SysOrgTable.ROOT_FLAG)
    private Boolean rootFlag;
    /**
     * 身份编码
     */
    @TableField(SysOrgTable.PROVINCE)
    private String province;
    /**
     * 市 编码
     */
    @TableField(SysOrgTable.CITY)
    private String city;
    /**
     * 区编码
     */
    @TableField(SysOrgTable.AREA)
    private String area;
    /**
     * 地址
     */
    @TableField(SysOrgTable.ADDRESS)
    private String address;
}
