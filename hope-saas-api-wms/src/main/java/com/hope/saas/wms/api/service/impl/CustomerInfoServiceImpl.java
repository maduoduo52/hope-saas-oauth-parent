package com.hope.saas.wms.api.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hope.saas.api.util.util.BeanUtil;
import com.hope.saas.api.util.util.MD5Utils;
import com.hope.saas.common.dto.wms.CustomerInfoDto;
import com.hope.saas.common.entity.util.Result;
import com.hope.saas.common.entity.wms.CustomerInfoEntity;
import com.hope.saas.common.enums.ResultCodeEnum;
import com.hope.saas.common.table.wms.CustomerInfoTable;
import com.hope.saas.wms.api.dao.CustomerInfoDao;
import com.hope.saas.wms.api.service.CustomerInfoService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户信息表
 *
 * @author Maduo
 * @date 2020-03-20 14:09:44
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerInfoServiceImpl extends ServiceImpl<CustomerInfoDao, CustomerInfoEntity> implements CustomerInfoService {


    @Override
    public Result saveCustomer(CustomerInfoDto customerInfoDto) {

        EntityWrapper wrapper = new EntityWrapper();
        wrapper.eq(CustomerInfoTable.TENANT_ID, customerInfoDto.getTenantId());
        wrapper.eq(CustomerInfoTable.SHIPPER_CODE, customerInfoDto.getShipperCode());
        CustomerInfoEntity entity = this.selectOne(wrapper);
        if (entity != null) {

            return Result.error("该shipperCode已被注册");
        }

        entity = BeanUtil.beanToBean(customerInfoDto, CustomerInfoEntity.class);
        entity.setSalt(RandomStringUtils.randomAlphanumeric(10));
        //生成16位随机秘钥
        entity.setUserKey(RandomStringUtils.randomAlphanumeric(16));
        //使用MD5对密码加密
        entity.setPassword(MD5Utils.encryptByMD5(entity.getPassword() + entity.getSalt()));

        boolean flag = this.insert(entity);
        if (flag) {
            return Result.success(entity.getUserKey(), ResultCodeEnum.SUCCESS.getDesc());
        } else {
            return Result.error("注册失败");
        }
    }
}