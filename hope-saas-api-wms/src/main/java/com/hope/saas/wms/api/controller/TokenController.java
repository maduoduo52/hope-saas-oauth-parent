package com.hope.saas.wms.api.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hope.saas.api.util.util.JwtUtil;
import com.hope.saas.common.constants.RedisKeyConstant;
import com.hope.saas.common.constants.TokenConstants;
import com.hope.saas.common.dto.wms.CustomerInfoDto;
import com.hope.saas.common.entity.util.Result;
import com.hope.saas.common.entity.wms.CustomerInfoEntity;
import com.hope.saas.common.enums.ResultCodeEnum;
import com.hope.saas.common.table.wms.CustomerInfoTable;
import com.hope.saas.wms.api.config.annotation.IgnoreLogin;
import com.hope.saas.wms.api.config.redis.RedisTemplateUtils;
import com.hope.saas.wms.api.service.CustomerInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Maduo
 * @date 2020/4/15 18:02
 */
@RestController
@RequestMapping("token")
public class TokenController {

    @Autowired
    private CustomerInfoService customerInfoService;

    @Autowired
    private RedisTemplateUtils redisTemplateUtils;

    @PostMapping("createToken")
    @IgnoreLogin
    public Result getToken(@RequestBody CustomerInfoDto customerInfoDto) throws Exception {

        if (StringUtils.isBlank(customerInfoDto.getTenantId()) || StringUtils.isBlank(customerInfoDto.getShipperCode())) {
            return Result.validationError("tenantId或shipperCode不能为空");
        }

        EntityWrapper wrapper = new EntityWrapper();
        wrapper.eq(CustomerInfoTable.TENANT_ID, customerInfoDto.getTenantId());
        wrapper.eq(CustomerInfoTable.SHIPPER_CODE, customerInfoDto.getShipperCode());
        CustomerInfoEntity entity = customerInfoService.selectOne(wrapper);
        if (entity == null) {
            return Result.error(ResultCodeEnum.CUSTOMER_NOT_EXIST, ResultCodeEnum.CUSTOMER_NOT_EXIST.getDesc());
        }

        String token = JwtUtil.createToken(customerInfoDto.getTenantId(), customerInfoDto.getShipperCode());
        if (StringUtils.isBlank(token)) {
            return Result.error(ResultCodeEnum.CREATE_TOKEN_ERROR);
        }

        if (!redisTemplateUtils.exists(RedisKeyConstant.WMS_API_CUSTOMER_INFO +
                customerInfoDto.getTenantId() + customerInfoDto.getShipperCode())) {
            //用户信息存储2天
            redisTemplateUtils.set(RedisKeyConstant.WMS_API_CUSTOMER_INFO +
                    customerInfoDto.getTenantId() + customerInfoDto.getShipperCode(), JSON.toJSONString(entity), TokenConstants.EXPIRATION * 2);
        }

        //token信息存储1天
        redisTemplateUtils.set(RedisKeyConstant.WMS_API_CREATE_TOKEN +
                customerInfoDto.getTenantId() + customerInfoDto.getShipperCode(), token, TokenConstants.EXPIRATION);

        return Result.success(token, ResultCodeEnum.SUCCESS.getDesc());
    }
}
