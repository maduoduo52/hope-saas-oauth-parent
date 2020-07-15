package com.hope.saas.wms.api.service.sys.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hope.saas.common.entity.wms.sys.SysUserMenuEntity;
import com.hope.saas.common.table.wms.sys.SysUserMenuTable;
import com.hope.saas.wms.api.config.mybatisplus.service.impl.BaseServerServiceImpl;
import com.hope.saas.wms.api.dao.sys.SysUserMenuDao;
import com.hope.saas.wms.api.service.sys.SysUserMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户 - 菜单 关联表
 *
 * @author Maduo
 * @date 2020-05-22 17:44:00
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserMenuServiceImpl extends BaseServerServiceImpl<SysUserMenuDao, SysUserMenuEntity> implements SysUserMenuService {
    @Override
    public boolean save(Long userId, Long[] menuIds) {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq(SysUserMenuTable.USER_ID, userId);
        physicsDelete(entityWrapper);
        if (menuIds != null) {
            for (Long menuId : menuIds) {
                SysUserMenuEntity entity = new SysUserMenuEntity();
                entity.setMenuId(menuId);
                entity.setUserId(userId);
                insert(entity);
            }
        }
        return true;
    }
}