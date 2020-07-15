package com.hope.saas.common.vo.wms;

import com.hope.saas.common.vo.util.BaseVo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 库存查询实体
 *
 * @author Maduo
 * @date 2020/3/27 16:19
 */
@Data
public class StockVo extends BaseVo {

    /**
     * 库存总量（换算）
     */
//    private String conversionTotalInventory;

    /**
     * 货品名称
     */
    private String goodsName;

    /**
     * 入库批次
     */
    private String incomingBatch;

    /**
     * 库存可用量
     */
    private BigDecimal remainingCount;

    /**
     * 剩余天数
     */
    private Integer restDay;

    /**
     * 生产日期
     */
    private Date productDate;

    /**
     * 货主货品编码
     */
    private String shipperGoodsCode;

    /**
     * 温层编码
     */
    private String temperatureLayerCode;

    /**
     * 零单位数量
     */
    private BigDecimal totalCount;

    /**
     * 整单位数量
     */
    private BigDecimal intUnitNumber;
}
