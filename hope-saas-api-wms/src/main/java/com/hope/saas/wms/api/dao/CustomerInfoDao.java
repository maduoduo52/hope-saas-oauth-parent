package com.hope.saas.wms.api.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hope.saas.common.entity.wms.CustomerInfoEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户信息表
 *
 * @author Maduo
 * @date 2020-03-20 14:09:44
 */
@Mapper
public interface CustomerInfoDao extends BaseMapper<CustomerInfoEntity> {
}