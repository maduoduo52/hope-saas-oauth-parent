package com.hope.saas.common.entity.wms.sys;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.hope.saas.common.entity.util.BaseEntity;
import com.hope.saas.common.table.wms.sys.SysPostTable;
import lombok.Data;


/**
 * 职位表
 *
 * @author Maduo
 * @date 2020-05-22 17:44:00
 */
@Data
@TableName("sys_post")
public class SysPostEntity extends BaseEntity {
    /**
     * 名称
     */
    @TableField(SysPostTable.NAME)
    private String name;
    /**
     * 状态  1 启用 0禁用
     */
    @TableField(SysPostTable.STATUS)
    private Boolean status;
    /**
     * 机构ID
     */
    @TableField(SysPostTable.ORG_ID)
    private Long orgId;
}
