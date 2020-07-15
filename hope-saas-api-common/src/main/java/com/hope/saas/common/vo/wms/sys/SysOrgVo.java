package com.hope.saas.common.vo.wms.sys;

import com.hope.saas.common.vo.util.BaseVo;
import lombok.Data;


/**
 * 组织机构表
 *
 * @author Maduo
 * @date 2020-05-22 17:44:00
 */
@Data
public class SysOrgVo extends BaseVo {
    /**
     * 菜单名称
     */
    private String name;
    /**
     * company 公司 department 部门
     */
    private String type;
    /**
     * 状态  1 启用 0禁用
     */
    private Integer status;
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
     * 身份编码
     */
    private String province;
    /**
     * 市 编码
     */
    private String city;
    /**
     * 区编码
     */
    private String area;
    /**
     * 地址
     */
    private String address;
}
