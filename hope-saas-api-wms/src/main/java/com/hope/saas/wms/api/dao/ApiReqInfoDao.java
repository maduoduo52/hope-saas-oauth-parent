package com.hope.saas.wms.api.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hope.saas.common.entity.wms.ApiReqInfoEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * api请求记录
 *
 * @author Maduo
 * @date 2020-03-20 14:09:44
 */
@Mapper
public interface ApiReqInfoDao extends BaseMapper<ApiReqInfoEntity> {
}