package com.hope.saas.wms.api.dao.sys;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hope.saas.common.entity.wms.sys.SysOrgEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 组织机构表
 *
 * @author Maduo
 * @date 2020-05-22 17:44:00
 */
@Mapper
public interface SysOrgDao extends BaseMapper<SysOrgEntity> {
}