package com.hope.saas.wms.api.dao.sys;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hope.saas.common.entity.wms.sys.SysUserMenuEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户 - 菜单 关联表
 *
 * @author Maduo
 * @date 2020-05-22 17:44:00
 */
@Mapper
public interface SysUserMenuDao extends BaseMapper<SysUserMenuEntity> {
}