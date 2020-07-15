package com.hope.saas.wms.api.service.sys.impl;

import com.hope.saas.common.entity.wms.sys.SysMenuEntity;
import com.hope.saas.wms.api.config.mybatisplus.service.impl.BaseServerServiceImpl;
import com.hope.saas.wms.api.dao.sys.SysMenuDao;
import com.hope.saas.wms.api.service.sys.SysMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 菜单表
 *
 * @author Maduo
 * @date 2020-05-22 17:44:00
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysMenuServiceImpl extends BaseServerServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {
}