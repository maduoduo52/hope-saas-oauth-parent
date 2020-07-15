package com.hope.saas.wms.api.controller;

import com.hope.saas.common.entity.util.Result;
import com.hope.saas.common.vo.wms.*;
import com.hope.saas.wms.api.config.annotation.IgnoreLogin;
import com.hope.saas.wms.api.config.annotation.SubmitValidate;
import com.hope.saas.wms.api.service.api.WmsCallbackServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Maduo
 * @date 2020/4/2 13:58
 */
@RestController
@RequestMapping("wms/callback")
public class WmsCallbackController {

    @Autowired
    private WmsCallbackServcie callbackServcie;

    /**
     * 入库回调
     *
     * @param storeManageDTO 入库数据
     * @return
     */
    @PostMapping("storeCallback")
    @SubmitValidate
    @IgnoreLogin
    public Result storeCallback(@RequestBody StoreManageBackVo storeManageDTO) {

        return callbackServcie.storeCallback(storeManageDTO);
    }

    /**
     * 出库库回调
     *
     * @param deliverManageDTO 出库数据
     * @return
     */
    @PostMapping("deliverCallback")
    @SubmitValidate
    @IgnoreLogin
    public Result deliverCallback(@RequestBody DeliverManageBackVo deliverManageDTO) {

        return callbackServcie.deliverCallback(deliverManageDTO);
    }

    /**
     * 库存调整推送
     *
     * @param adjustManageDTO 库存调整数据
     * @return
     */
    @PostMapping("stockAdjust")
    @SubmitValidate
    @IgnoreLogin
    public Result stockAdjust(@RequestBody StockAdjustManageVo adjustManageDTO) {

        return callbackServcie.stockAdjust(adjustManageDTO);
    }

    /**
     * 库存盘点推送
     *
     * @param inventoryManageDTO 库存盘点数据
     * @return
     */
    @PostMapping("stockInventory")
    @SubmitValidate
    @IgnoreLogin
    public Result stockInventory(@RequestBody StockInventoryManageVo inventoryManageDTO) {

        return callbackServcie.stockInventory(inventoryManageDTO);
    }

    /**
     * 移库移位推送
     *
     * @param moveManageDTO 移库移位数据
     * @return
     */
    @PostMapping("stockMove")
    @SubmitValidate
    @IgnoreLogin
    public Result stockMove(@RequestBody StockMoveManageVo moveManageDTO) {

        return callbackServcie.stockMove(moveManageDTO);
    }
}
