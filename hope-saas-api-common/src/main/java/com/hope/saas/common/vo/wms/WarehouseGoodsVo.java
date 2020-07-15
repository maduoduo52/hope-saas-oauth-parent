package com.hope.saas.common.vo.wms;

import com.hope.saas.common.vo.util.BaseVo;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 货品信息查询
 *
 * @author Maduo
 * @date 2020/3/27 13:14
 */
@Data
public class WarehouseGoodsVo extends BaseVo {

    /**
     * 货品名称
     */
    private String goodsName;

    /**
     * 货主货品编码
     */
    private String shipperGoodsCode;

    /**
     * 国际编码
     */
    private String internationalCode;

    /**
     * 货品类别编码
     */
    private String goodsTypeCode;

    /**
     * 货品规格
     */
    private String goodsSpecifications;

    /**
     * 整单位
     */
    private String bigUnit;

    /**
     * 换算率
     */
    private BigDecimal conversionRate;

    /**
     * 零单位
     */
    private String smallUnit;

    /**
     * 默认单位
     */
    private String defaultUnit;

    /**
     * 件重(千克)
     */
    private BigDecimal weight;

    /**
     * 计费件重(千克)
     */
    private BigDecimal chargeWeight;

    /**
     * 单板数量
     */
    private BigDecimal veneerWeight;

    /**
     * 长（厘米）
     */
    private BigDecimal goodsLength;

    /**
     * 宽（厘米）
     */
    private BigDecimal goodsWide;

    /**
     * 高（厘米）
     */
    private BigDecimal goodsHight;

    /**
     * 温层编码
     */
    private String temperatureLayerCode;

    /**
     * 单价（元/件）
     */
    private BigDecimal goodsPrice;

    /**
     * 保质期（天）
     */
    private BigDecimal shelfLife;

    /**
     * 临期天数
     */
    private BigDecimal daysDue;

    /**
     * 安全库存
     */
    private BigDecimal safeStock;

    /**
     * 包装类型
     */
    private String packagTypeName;

    /**
     * 是否抄码
     */
    private Integer isCopyCode;

    /**
     * 批次属性
     */
    private String batchAttributeName;

    /**
     * 允收期
     */
    private BigDecimal dueInDays;

    /**
     * 允出期
     */
    private BigDecimal dueOutDays;

    /**
     * 辅助单位
     */
    private String auxiliaryUnits;

    /**
     * 是否越库
     */
    private Integer isOverWarehouse;

    /**
     * 越库是否分拣
     */
    private Integer isOverWarehousePick;

    /**
     * 解冻标签
     */
    private Integer isUnfreeze;

    /**
     * 是否装卸
     */
    private Integer isLoad;

    /**
     * 一托几品
     */
    private Integer blockGoodsNum;

    /**
     * 第三方货品编码
     */
    private String thirdProCode;

}
