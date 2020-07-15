package com.hope.saas.wms.api.service.api.impl;

import cn.hutool.http.ContentType;
import cn.hutool.http.Header;
import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSON;
import com.hope.saas.api.util.exception.WmsApiException;
import com.hope.saas.api.util.util.AESCodeUtil;
import com.hope.saas.api.util.util.HttpPostUtil;
import com.hope.saas.common.entity.util.Result;
import com.hope.saas.common.entity.wms.CustomerInfoEntity;
import com.hope.saas.common.enums.ExceptionEnum;
import com.hope.saas.common.enums.SendEnum;
import com.hope.saas.common.vo.wms.*;
import com.hope.saas.wms.api.service.CustomerInfoService;
import com.hope.saas.wms.api.service.api.WmsCallbackServcie;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Maduo
 * @date 2020/3/30 14:55
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class WmsCallbackServiceImpl implements WmsCallbackServcie {

    @Autowired
    private CustomerInfoService customerInfoService;

    @Override
    public Result storeCallback(StoreManageBackVo storeManageDTO) {
        CustomerInfoEntity customerInfoEntity = getCustomerInfoEntity(storeManageDTO.getApiCusId());

        if (StringUtils.isEmpty(customerInfoEntity.getStoreUrl())) {
            throw new WmsApiException(ExceptionEnum.CALLBACK_URL_NOT_EXIST.getMessage());
        }

        if (sendThirdPost(customerInfoEntity.getStoreUrl(), JSON.toJSONString(storeManageDTO), customerInfoEntity.getUserKey()))
            return Result.success();

        return Result.error("数据推送失败");
    }

    @Override
    public Result deliverCallback(DeliverManageBackVo deliverManageDTO) {
        CustomerInfoEntity customerInfoEntity = getCustomerInfoEntity(deliverManageDTO.getApiCusId());

        if (StringUtils.isEmpty(customerInfoEntity.getDeliverUrl())) {
            throw new WmsApiException(ExceptionEnum.CALLBACK_URL_NOT_EXIST.getMessage());
        }
        if (sendThirdPost(customerInfoEntity.getDeliverUrl(), JSON.toJSONString(deliverManageDTO), customerInfoEntity.getUserKey()))
            return Result.success();

        return Result.error("数据推送失败");
    }

    @Override
    public Result stockAdjust(StockAdjustManageVo adjustManageDTO) {
        CustomerInfoEntity customerInfoEntity = getCustomerInfoEntity(adjustManageDTO.getApiCusId());
        if (StringUtils.isEmpty(customerInfoEntity.getAdjustUrl())) {
            throw new WmsApiException(ExceptionEnum.CALLBACK_URL_NOT_EXIST.getMessage());
        }

        if (sendThirdPost(customerInfoEntity.getAdjustUrl(), JSON.toJSONString(adjustManageDTO), customerInfoEntity.getUserKey()))
            return Result.success();

        return Result.error("数据推送失败");
    }

    @Override
    public Result stockInventory(StockInventoryManageVo inventoryManageDTO) {
        CustomerInfoEntity customerInfoEntity = getCustomerInfoEntity(inventoryManageDTO.getApiCusId());
        if (StringUtils.isEmpty(customerInfoEntity.getInventoryUrl())) {
            throw new WmsApiException(ExceptionEnum.CALLBACK_URL_NOT_EXIST.getMessage());
        }
        if (sendThirdPost(customerInfoEntity.getInventoryUrl(), JSON.toJSONString(inventoryManageDTO), customerInfoEntity.getUserKey()))
            return Result.success();

        return Result.error("数据推送失败");
    }

    @Override
    public Result stockMove(StockMoveManageVo moveManageDTO) {
        CustomerInfoEntity customerInfoEntity = getCustomerInfoEntity(moveManageDTO.getApiCusId());
        if (StringUtils.isEmpty(customerInfoEntity.getMoveUrl())) {
            throw new WmsApiException(ExceptionEnum.CALLBACK_URL_NOT_EXIST.getMessage());
        }

        if (sendThirdPost(customerInfoEntity.getMoveUrl(), JSON.toJSONString(moveManageDTO), customerInfoEntity.getUserKey()))
            return Result.success();

        return Result.error("数据推送失败");
    }

    private CustomerInfoEntity getCustomerInfoEntity(Long apiCusId) {
        CustomerInfoEntity customerInfoEntity = customerInfoService.selectById(apiCusId);
        if (customerInfoEntity == null) {
            throw new WmsApiException(ExceptionEnum.CUSTOMER_NOT_EXIST.getMessage());
        }
        return customerInfoEntity;
    }

    private boolean sendThirdPost(String url, String data, String aesKey) {
        log.warn("===>> 收到推送第三方请求：{}，数据：{}", url, data);

        if (SendEnum.NOT_NEED_SEND.name().equals(url)) {
            //如果不需要发送，直接返回true
            return true;
        }

        Map<String, String> headerMap = new HashMap<>();
        headerMap.put(Header.CONTENT_TYPE.toString(), ContentType.JSON.toString());

        String post = HttpPostUtil.sendPost(url, headerMap, AESCodeUtil.encode(data, aesKey));

        log.info("===>> 推送第三方结果：{}", post);
        if (StringUtils.isNotBlank(post)) {
            try {
                Result result = JSON.parseObject(post, Result.class);
                return result.getCode() == HttpStatus.HTTP_OK;
            } catch (Exception e) {
                throw new WmsApiException(ExceptionEnum.RES_MUST_RESULT.getMessage());
            }
        } else {
            return false;
        }
    }
}
