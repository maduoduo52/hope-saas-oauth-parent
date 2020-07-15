package com.hope.saas.common.entity.wms.sys;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.hope.saas.common.entity.util.BaseEntity;
import com.hope.saas.common.table.wms.sys.SysUserPostTable;
import lombok.Data;


/**
 * 用户 - 职位 关联表
 *
 * @author Maduo
 * @date 2020-05-22 17:44:00
 */
@Data
@TableName("sys_user_post")
public class SysUserPostEntity extends BaseEntity {
    /**
     * 用户ID
     */
    @TableField(SysUserPostTable.USER_ID)
    private Long userId;
    /**
     * 职位ID
     */
    @TableField(SysUserPostTable.POST_ID)
    private Long postId;
}
