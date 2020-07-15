package com.hope.saas.common.vo.wms.sys;

import com.hope.saas.common.vo.util.BaseVo;
import lombok.Data;


/**
 * 职位表
 *
 * @author Maduo
 * @date 2020-05-22 17:44:00
 */
@Data
public class SysPostVo extends BaseVo {
    /**
     * 名称
     */
    private String name;
    /**
     * 状态  1 启用 0禁用
     */
    private Integer status;
    /**
     * 机构ID
     */
    private String orgId;
}
