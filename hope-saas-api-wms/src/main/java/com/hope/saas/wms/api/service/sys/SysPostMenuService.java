package com.hope.saas.wms.api.service.sys;

import com.baomidou.mybatisplus.service.IService;
import com.hope.saas.common.entity.wms.sys.SysPostMenuEntity;

/**
 * 职位 - 菜单 关联表
 *
 * @author Maduo
 * @date 2020-05-22 17:44:00
 */
public interface SysPostMenuService extends IService<SysPostMenuEntity> {
    boolean save(Long postId, Long[] menuIds);
}