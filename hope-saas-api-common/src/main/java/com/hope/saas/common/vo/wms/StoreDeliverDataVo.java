package com.hope.saas.common.vo.wms;

import com.hope.saas.common.vo.util.BaseVo;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Maduo
 * @date 2020/4/15 10:53
 */
@Data
public class StoreDeliverDataVo extends BaseVo {

    /**
     * 货主货品编码
     */
    private String shipperGoodsCode;

    /**
     * 货品名称
     */
    private String goodsName;

    /**
     * 整单位预约数量
     */
    private BigDecimal bigUnitPre;

    /**
     * 零单位预约数量
     */
    private BigDecimal smallUnitPre;

    /**
     * 整单位上架/出库数量
     */
    private BigDecimal bigUnitUpper;

    /**
     * 零单位上架/出库数量
     */
    private BigDecimal smallUnitUpper;

    private BigDecimal defaultUnitUpper;

    private BigDecimal defaultUnitPre;
}
