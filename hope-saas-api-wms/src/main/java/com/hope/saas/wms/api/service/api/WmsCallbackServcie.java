package com.hope.saas.wms.api.service.api;


import com.hope.saas.common.entity.util.Result;
import com.hope.saas.common.vo.wms.*;

/**
 * @author Maduo
 * @date 2020/3/30 14:54
 */
public interface WmsCallbackServcie {

    Result storeCallback(StoreManageBackVo storeManageDTO);

    /**
     * 出库库回调
     *
     * @param deliverManageDTO 出库数据
     * @return
     */
    Result deliverCallback(DeliverManageBackVo deliverManageDTO);

    /**
     * 库存调整推送
     *
     * @param adjustManageDTO 调整数据
     * @return
     */
    Result stockAdjust(StockAdjustManageVo adjustManageDTO);

    /**
     * 库存盘点推送
     *
     * @param inventoryManageDTO 库存盘点数据
     * @return
     */
    Result stockInventory(StockInventoryManageVo inventoryManageDTO);

    /**
     * 移库移位推送
     *
     * @param moveManageDTO 移库移位数据
     * @return
     */
    Result stockMove(StockMoveManageVo moveManageDTO);
}
