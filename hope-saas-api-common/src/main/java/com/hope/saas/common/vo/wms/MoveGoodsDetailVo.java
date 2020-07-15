package com.hope.saas.common.vo.wms;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Maduo
 * @date 2020/3/27 16:39
 */
@Data
public class MoveGoodsDetailVo {

    /**
     * 货主货品编码
     */
    private String shipperGoodsCode;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 源库位编码
     */
    private String sourceLocationCode;

    /**
     * 目标库位编码
     */
    private String targetLocationCode;

    /**
     * 移库移位数量
     */
    private BigDecimal transferNumber;

}
