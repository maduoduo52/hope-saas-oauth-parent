package com.hope.saas.common.dto.wms;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Maduo
 * @date 2020/3/27 16:35
 */
@Data
public class InventoryGoodsDetailDTO {

    /**
     * 货主货品编码
     */
    private String shipperGoodsCode;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 生产日期
     */
    private Date proDate;

    /**
     * 库存数量
     */
    private BigDecimal storeCount;

    /**
     * 盘点数量
     */
    private BigDecimal inventoryCount;

    /**
     * 差异数量
     */
    private BigDecimal diffCount;

    /**
     * 调整结果
     * 0:正常，1:盘盈，2:盘亏
     */
    private String inventoryResult;

    /**
     * 所属库位
     */
    private String storageLocationCode;
}
