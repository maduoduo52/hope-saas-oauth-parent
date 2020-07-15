package com.hope.saas.wms.api.service.api.impl;

import cn.hutool.http.ContentType;
import cn.hutool.http.Header;
import com.alibaba.fastjson.JSON;
import com.hope.saas.api.util.exception.WmsApiException;
import com.hope.saas.api.util.util.HttpPostUtil;
import com.hope.saas.common.constants.Constant;
import com.hope.saas.common.constants.WmsInterfaceTokenConstant;
import com.hope.saas.common.dto.wms.DeliverManageDTO;
import com.hope.saas.common.dto.wms.StoreManageDTO;
import com.hope.saas.common.dto.wms.WarehouseGoodsModifyDTO;
import com.hope.saas.common.dto.wms.query.StockQueryDTO;
import com.hope.saas.common.entity.util.Result;
import com.hope.saas.common.entity.wms.CustomerInfoEntity;
import com.hope.saas.common.enums.ExceptionEnum;
import com.hope.saas.common.enums.ResultCodeEnum;
import com.hope.saas.common.enums.StoreTypeEnum;
import com.hope.saas.common.vo.wms.*;
import com.hope.saas.wms.api.service.api.WmsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Maduo
 * @date 2020/3/30 15:08
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class WmsServiceImpl implements WmsService {

    @Value("${wms.api.url}")
    private String wmsApiUrl;

    @Value("${wms.api.token}")
    private String token;

    @Override
    public Result queryStockInfo(String data) {

        StockQueryDTO queryDTO = JSON.parseObject(data, StockQueryDTO.class);

        if (queryDTO.isUsePage()) {
            if (null == queryDTO.getPageNum() || null == queryDTO.getPageSize()) {
                return Result.validationError(ExceptionEnum.PAGE_DATA_IS_NULL.getMessage());
            }
        }
        return getWmsResult(data, WmsInterfaceTokenConstant.QUERY_STOCK_INFO, StockVo.class, true);
    }

    @Override
    public Result saveStore(String data) {

        StoreManageDTO manageDTO = JSON.parseObject(data, StoreManageDTO.class);
        if (manageDTO.getStoreTypeCode().equals(StoreTypeEnum.TH.getCode()) && StringUtils.isBlank(manageDTO.getShopCode())) {
            return Result.validationError("退货入库，请传输门店信息");
        }

        CustomerInfoEntity entity = Constant.get().getCustomerInfoEntity();
        if (entity == null) {
            return Result.error(ResultCodeEnum.CUSTOMER_NOT_EXIST, ResultCodeEnum.CUSTOMER_NOT_EXIST.getDesc());
        }
        manageDTO.setSourceCode(entity.getSourceCode());

        return getWmsResult(JSON.toJSONString(manageDTO), WmsInterfaceTokenConstant.SAVE_STORE_MANAGE, null, false);
    }

    @Override
    public Result queryOrderStatus(String data) {

        return getWmsResult(data, WmsInterfaceTokenConstant.QUERY_ORDER_STATUS, OrderStatusVo.class, false);
    }

    @Override
    public Result cacelOrDeleteOrder(String data) {

        return getWmsResult(data, WmsInterfaceTokenConstant.CANCEL_DELETE_ORDER, null, false);
    }

    @Override
    public Result saveShop(String data) {

        return getWmsResult(data, WmsInterfaceTokenConstant.SAVE_SHOP_URL, null, false);
    }

    @Override
    public Result queryShop(String data) {

        return getWmsResult(data, WmsInterfaceTokenConstant.QUERY_SHOP_URL, WarehouseShopVo.class, true);
    }

    @Override
    public Result saveGoods(String data) {

        return getWmsResult(data, WmsInterfaceTokenConstant.SAVE_GOODS_URL, null, false);
    }

    @Override
    public Result queryGoods(String data) {

        return getWmsResult(data, WmsInterfaceTokenConstant.QUERY_GOODS_URL, WarehouseGoodsVo.class, true);
    }

    @Override
    public Result saveDeliver(String data) {

        DeliverManageDTO deliverManageDTO = JSON.parseObject(data, DeliverManageDTO.class);
        CustomerInfoEntity entity = Constant.get().getCustomerInfoEntity();
        if (entity == null) {
            return Result.error(ResultCodeEnum.CUSTOMER_NOT_EXIST, ResultCodeEnum.CUSTOMER_NOT_EXIST.getDesc());
        }

        deliverManageDTO.setSourceCode(entity.getSourceCode());
        return getWmsResult(JSON.toJSONString(deliverManageDTO), WmsInterfaceTokenConstant.SAVE_DELIVER_MANAGE, null, false);
    }

    @Override
    public Result modifyGoods(String data) {
        List<WarehouseGoodsModifyDTO> list = JSON.parseArray(data, WarehouseGoodsModifyDTO.class);
        return getWmsResult(JSON.toJSONString(list), WmsInterfaceTokenConstant.MODIFY_GOODS_URL, null, false);
    }

    @Override
    public Result queryStoreAndDeliverManageData(String data) {

        return getWmsResult(data, WmsInterfaceTokenConstant.QUERY_STORE_AND_DELIVER_MANAGE_DATA, StoreDeliverDataVo.class, true);
    }

    /**
     * 获取wms请求结果
     *
     * @param data     请求参数
     * @param postUrl  请求地址
     * @param clazz    类
     * @param listFlag 是否为list
     * @return
     */
    private Result getWmsResult(String data, String postUrl, Class clazz, Boolean listFlag) {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put(WmsInterfaceTokenConstant.WMS_TOKEN_NAME, token);
        headerMap.put(Header.CONTENT_TYPE.toString(), ContentType.JSON.toString());

        if (StringUtils.isEmpty(wmsApiUrl)) {
            log.error("===>>wms系统数据交互URL为空");
            throw new WmsApiException("wms数据交互URL为空");
        }

        log.info("===>> wms数据交互地址：{}", wmsApiUrl + postUrl);

        String res = HttpPostUtil.sendPost(wmsApiUrl + postUrl, headerMap, data);

        if (StringUtils.isBlank(res)) {
            throw new WmsApiException(ResultCodeEnum.WMS_REQ_ERROR.getDesc());
        }
        Result result = JSON.parseObject(res, Result.class);
        log.info("===》 WMS请求返回：{}", JSON.toJSONString(result));
        if (result.getCode() != ResultCodeEnum.WMS_SUCCESS_CODE.getStatus()) {
            return Result.error(ResultCodeEnum.WMS_REQ_ERROR, result.getMessage());
        } else {
            if (Objects.isNull(result.getData())) {
                return Result.success(result.getMessage());
            } else {
                Object resData;
                if (listFlag) {
                    resData = JSON.parseArray(JSON.toJSONString(result.getData()), clazz);
                } else {
                    resData = JSON.parseObject(JSON.toJSONString(result.getData()), clazz);
                }
                log.info("===》 api返回数据：{}", JSON.toJSONString(resData));
                return Result.success(resData, result.getMessage());
            }
        }
    }
}
