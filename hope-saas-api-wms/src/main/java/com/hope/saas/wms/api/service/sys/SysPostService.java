package com.hope.saas.wms.api.service.sys;

import com.baomidou.mybatisplus.service.IService;
import com.hope.saas.common.entity.wms.sys.SysPostEntity;

import java.util.List;

/**
 * 职位表
 *
 * @author Maduo
 * @date 2020-05-22 17:44:00
 */
public interface SysPostService extends IService<SysPostEntity> {
    List<SysPostEntity> selectByLoginName(String userName);
}