package com.hope.saas.common.vo.wms.sys;

import com.hope.saas.common.vo.util.BaseVo;
import lombok.Data;


/**
 * 职位 - 菜单 关联表
 *
 * @author Maduo
 * @date 2020-05-22 17:44:00
 */
@Data
public class SysPostMenuVo extends BaseVo {
    /**
     *
     */
    private String menuId;
    /**
     *
     */
    private String postId;
}
