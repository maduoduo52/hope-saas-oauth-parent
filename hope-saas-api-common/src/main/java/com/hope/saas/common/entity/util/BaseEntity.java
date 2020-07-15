package com.hope.saas.common.entity.util;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.Version;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.hope.saas.common.table.util.BaseTable;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Maduo
 * @date 2020/3/20 10:44
 */
@Data
public class BaseEntity implements Serializable {

    /**
     * id
     */
    @TableId(value = BaseTable.ID, type = IdType.AUTO)
    @TableField(value = BaseTable.ID)
    protected Long id;

    /**
     * 添加时间
     */
    @TableField(value = BaseTable.ADD_TIME, fill = FieldFill.INSERT)
    protected Date addTime;


    /**
     * 修改时间
     */
    @TableField(value = BaseTable.UPDATE_TIME, fill = FieldFill.UPDATE)
    protected Date updateTime;

    /**
     * 版本号
     */
    @TableField(value = BaseTable.VERSION, fill = FieldFill.INSERT)
    @Version
    protected Integer version;

    /**
     * 逻辑删除字段
     */
    @TableLogic
    @TableField(value = BaseTable.DELETE_FLAG, fill = FieldFill.INSERT)
    private Boolean deleteFlag;

    /**
     * s
     * 备注
     */
    @TableField(value = BaseTable.BA_REMARK, fill = FieldFill.UPDATE)
    protected String baRemark;
}
