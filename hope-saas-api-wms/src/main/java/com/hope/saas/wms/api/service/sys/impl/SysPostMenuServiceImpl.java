package com.hope.saas.wms.api.service.sys.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hope.saas.common.entity.wms.sys.SysPostMenuEntity;
import com.hope.saas.common.table.wms.sys.SysPostMenuTable;
import com.hope.saas.wms.api.config.mybatisplus.service.impl.BaseServerServiceImpl;
import com.hope.saas.wms.api.dao.sys.SysPostMenuDao;
import com.hope.saas.wms.api.service.sys.SysPostMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 职位 - 菜单 关联表
 *
 * @author Maduo
 * @date 2020-05-22 17:44:00
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysPostMenuServiceImpl extends BaseServerServiceImpl<SysPostMenuDao, SysPostMenuEntity> implements SysPostMenuService {

    @Override
    public boolean save(Long postId, Long[] menuIds) {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq(SysPostMenuTable.POST_ID, postId);
        physicsDelete(entityWrapper);
        if (menuIds != null) {
            for (Long menuId : menuIds) {
                SysPostMenuEntity entity = new SysPostMenuEntity();
                entity.setMenuId(menuId);
                entity.setPostId(postId);
                insert(entity);
            }
        }
        return true;
    }
}