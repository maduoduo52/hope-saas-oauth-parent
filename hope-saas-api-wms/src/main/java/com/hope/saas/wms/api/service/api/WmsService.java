package com.hope.saas.wms.api.service.api;

import com.hope.saas.common.entity.util.Result;

/**
 * @author Maduo
 * @date 2020/3/30 15:08
 */
public interface WmsService {

    /**
     * 库存查询
     *
     * @param data 加密参数
     * @return
     */
    Result queryStockInfo(String data);

    /**
     * 入库订单
     *
     * @param data 加密参数
     * @return
     */
    Result saveStore(String data);

    /**
     * 出入库单状态查询
     *
     * @param data 加密参数
     * @return
     */
    Result queryOrderStatus(String data);

    /**
     * 出入库单取消/删除
     *
     * @param data 加密参数
     * @return
     */
    Result cacelOrDeleteOrder(String data);

    /**
     * 门店数据保存
     *
     * @param data 加密参数
     * @return
     */
    Result saveShop(String data);

    /**
     * 门店信息查询
     *
     * @param data 加密参数
     * @return
     */
    Result queryShop(String data);

    /**
     * 保存货品信息
     *
     * @param data 加密参数
     * @return
     */
    Result saveGoods(String data);

    /**
     * 货品信息查询
     *
     * @param data 加密参数
     * @return
     */
    Result queryGoods(String data);

    /**
     * 出库订单
     *
     * @param data 加密参数
     * @return
     */
    Result saveDeliver(String data);

    /**
     * 修改货品信息
     *
     * @param data
     * @return
     */
    Result modifyGoods(String data);

    /**
     * 出入库数据查询
     *
     * @param data 加密参数
     * @return
     */
    Result queryStoreAndDeliverManageData(String data);
}
