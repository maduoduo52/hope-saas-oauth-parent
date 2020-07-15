package com.hope.saas.wms.api.controller;

import com.hope.saas.common.dto.wms.OrderCancelDTO;
import com.hope.saas.common.dto.wms.StoreManageDTO;
import com.hope.saas.common.dto.wms.query.OrderQueryDTO;
import com.hope.saas.common.dto.wms.query.StoreDeliverDataQueryDTO;
import com.hope.saas.common.entity.util.Result;
import com.hope.saas.wms.api.config.annotation.ReqAop;
import com.hope.saas.wms.api.config.annotation.SubmitValidate;
import com.hope.saas.wms.api.service.api.WmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Maduo
 * @date 2020/3/30 14:27
 */
@RestController
@RequestMapping("/api/wmsStore")
public class WmsStoreController {

    @Autowired
    private WmsService wmsService;

    /**
     * 入库订单
     *
     * @param data 加密参数
     * @return
     */
    @PostMapping("saveStore")
    @SubmitValidate
    @ReqAop(valiedArgs = true, clazz = StoreManageDTO.class)
    public Result saveStore(@RequestBody String data) {

        return wmsService.saveStore(data);
    }

    /**
     * 出入库单状态查询
     *
     * @param data 加密参数
     * @return
     */
    @PostMapping("queryOrderStatus")
    @SubmitValidate
    @ReqAop(valiedArgs = true, clazz = OrderQueryDTO.class, aes = true)
    public Result queryOrderStatus(@RequestBody String data) {

        return wmsService.queryOrderStatus(data);
    }

    /**
     * 出入库单取消/删除
     * p
     *
     * @param data 加密参数
     * @return
     */
    @PostMapping("cancelOrDeleteOrder")
    @SubmitValidate
    @ReqAop(valiedArgs = true, clazz = OrderCancelDTO.class)
    public Result cacelOrDeleteOrder(@RequestBody String data) {

        return wmsService.cacelOrDeleteOrder(data);
    }

    /**
     * 出入库数据查询
     *
     * @param data 加密参数
     * @return
     */
    @PostMapping("queryStoreAndDeliverManageData")
    @SubmitValidate
    @ReqAop(valiedArgs = true, clazz = StoreDeliverDataQueryDTO.class, aes = true)
    public Result queryStoreAndDeliverManageData(@RequestBody String data) {
        return wmsService.queryStoreAndDeliverManageData(data);
    }
}
