package com.hope.saas.wms.api.service.sys.impl;

import com.hope.saas.common.entity.wms.sys.SysPostEntity;
import com.hope.saas.wms.api.config.mybatisplus.service.impl.BaseServerServiceImpl;
import com.hope.saas.wms.api.dao.sys.SysPostDao;
import com.hope.saas.wms.api.service.sys.SysPostService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 职位表
 *
 * @author Maduo
 * @date 2020-05-22 17:44:00
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysPostServiceImpl extends BaseServerServiceImpl<SysPostDao, SysPostEntity> implements SysPostService {
    @Override
    public List<SysPostEntity> selectByLoginName(String userName) {
        return baseMapper.selectByLoginName(userName);
    }
}