package com.hope.saas.wms.api.service;

import com.baomidou.mybatisplus.service.IService;
import com.hope.saas.common.dto.wms.CustomerInfoDto;
import com.hope.saas.common.entity.util.Result;
import com.hope.saas.common.entity.wms.CustomerInfoEntity;

/**
 * 用户信息表
 *
 * @author Maduo
 * @date 2020-03-20 14:09:44
 */
public interface CustomerInfoService extends IService<CustomerInfoEntity> {

    Result saveCustomer(CustomerInfoDto customerInfoDto);
}