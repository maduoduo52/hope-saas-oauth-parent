package com.hope.saas.wms.api.service.sys;

import com.baomidou.mybatisplus.service.IService;
import com.hope.saas.common.entity.wms.sys.SysUserPostEntity;

/**
 * 用户 - 职位 关联表
 *
 * @author Maduo
 * @date 2020-05-22 17:44:00
 */
public interface SysUserPostService extends IService<SysUserPostEntity> {
    boolean saveUser(Long id, Long[] userIds);
}