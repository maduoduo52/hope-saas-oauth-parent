package com.hope.saas.common.dto.wms.sys;

import com.hope.saas.common.dto.util.BaseDTO;
import lombok.Data;


/**
 * 职位表
 *
 * @author Maduo
 * @date 2020-05-22 17:44:00
 */
@Data
public class SysPostDto extends BaseDTO {
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
