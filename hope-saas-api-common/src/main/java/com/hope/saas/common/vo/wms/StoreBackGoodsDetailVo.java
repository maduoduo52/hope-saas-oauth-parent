package com.hope.saas.common.vo.wms;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 入库反馈货品对象
 *
 * @author Maduo
 * @date 2020/3/27 16:05
 */
@Data
public class StoreBackGoodsDetailVo {

    /**
     * 货主货品编码
     */
    private String shipperGoodsCode;

    /**
     * 入库批次号
     */
    private String batchNo;

    /**
     * 生产日期
     */
    private String productTime;

    /**
     * 整单位上架数量
     */
    private BigDecimal bigUnitUpper;

    /**
     * 零单位上架数量
     */
    private BigDecimal smallUnitUpper;

    /**
     * 默认单位上架数量
     */
    private BigDecimal defaultUnitUpper;
}
