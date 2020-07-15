package com.hope.saas.common.vo.wms;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 出库反馈货品对象
 *
 * @author Maduo
 * @date 2020/3/27 16:08
 */
@Data
public class DeliverBackGoodsDetailVo {

    /**
     * 货主货品编码
     */
    private String shipperGoodsCode;

    /**
     * 入库批次号
     */
    private String batchNo;

    /**
     * 整单位拣货数量
     */
    private BigDecimal bigUnitPick;

    /**
     * 零单位拣货数量
     */
    private BigDecimal smallUnitPick;

    /**
     * 默认单位拣货数量
     */
    private BigDecimal defaultUnitUpper;
}
