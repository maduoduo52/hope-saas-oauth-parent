package com.hope.saas.wms.api.service.sys;

import com.baomidou.mybatisplus.service.IService;
import com.hope.saas.common.entity.wms.sys.SysUserMenuEntity;

/**
 * 用户 - 菜单 关联表
 *
 * @author Maduo
 * @date 2020-05-22 17:44:00
 */
public interface SysUserMenuService extends IService<SysUserMenuEntity> {
    boolean save(Long userId, Long[] menuIds);
}