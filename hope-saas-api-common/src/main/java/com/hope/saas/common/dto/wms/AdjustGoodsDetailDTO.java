package com.hope.saas.common.dto.wms;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 调整详情实体
 *
 * @author Maduo
 * @date 2020/3/27 16:29
 */
@Data
public class AdjustGoodsDetailDTO {

    /**
     * 货主货品编码
     */
    private String shipperGoodsCode;

    /**
     * 调整前批次号
     */
    private String batchNo;

    /**
     * 调整批次号
     */
    private String adjustBatch;

    /**
     * 生产日期
     */
    private Date productDate;

    /**
     * 库存数量
     */
    private BigDecimal storeCount;

    /**
     * 调整数量
     */
    private BigDecimal adjustCount;

    /**
     * 差异数量
     */
    private BigDecimal diffCount;

    /**
     * 调整结果
     * 0:正常，1:增加，2:减少
     */
    private String adjustResult;

    /**
     * 所属库位
     */
    private String storageLocationCode;
}
