package com.hope.saas.wms.api.service.sys.impl;

import com.hope.saas.common.entity.wms.sys.SysOrgEntity;
import com.hope.saas.wms.api.config.mybatisplus.service.impl.BaseServerServiceImpl;
import com.hope.saas.wms.api.dao.sys.SysOrgDao;
import com.hope.saas.wms.api.service.sys.SysOrgService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 组织机构表
 *
 * @author Maduo
 * @date 2020-05-22 17:44:00
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysOrgServiceImpl extends BaseServerServiceImpl<SysOrgDao, SysOrgEntity> implements SysOrgService {
}