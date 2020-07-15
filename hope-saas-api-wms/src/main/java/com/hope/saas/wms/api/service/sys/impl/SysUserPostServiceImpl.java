package com.hope.saas.wms.api.service.sys.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hope.saas.common.entity.wms.sys.SysUserPostEntity;
import com.hope.saas.common.table.wms.sys.SysUserPostTable;
import com.hope.saas.wms.api.config.mybatisplus.service.impl.BaseServerServiceImpl;
import com.hope.saas.wms.api.dao.sys.SysUserPostDao;
import com.hope.saas.wms.api.service.sys.SysUserPostService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户 - 职位 关联表
 *
 * @author Maduo
 * @date 2020-05-22 17:44:00
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserPostServiceImpl extends BaseServerServiceImpl<SysUserPostDao, SysUserPostEntity> implements SysUserPostService {
    @Override
    public boolean saveUser(Long postId, Long[] userIds) {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq(SysUserPostTable.POST_ID, postId);
        physicsDelete(entityWrapper);
        if (userIds != null && userIds.length > 0) {
            for (Long userId : userIds) {
                SysUserPostEntity sys = new SysUserPostEntity();
                sys.setPostId(postId);
                sys.setUserId(userId);
                insert(sys);
            }
        }
        return true;
    }
}