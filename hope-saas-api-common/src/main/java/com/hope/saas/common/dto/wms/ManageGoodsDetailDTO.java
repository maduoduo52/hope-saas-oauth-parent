package com.hope.saas.common.dto.wms;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Maduo
 * @date 2020/3/27 15:42
 */
@Data
public class ManageGoodsDetailDTO {

    /**
     * 货主货品编码
     */
    @NotBlank(message = "shipperGoodsCode不能为空")
    private String shipperGoodsCode;

    /**
     * 整单位预约数量
     */
    private BigDecimal bigUnitPre;

    /**
     * 零单位预约数量
     */
    private BigDecimal smallUnitPre;

    /**
     * 默认单位预约数量
     */
    @NotNull(message = "defaultUnitPre不能为空")
    @DecimalMin(value = "0", message = "defaultUnitPre最小值为0")
    private BigDecimal defaultUnitPre;

    /**
     * 整单位价格
     */
    private BigDecimal price;

    @Min(value = 1, message = "yxSort最小值为1")
    private Integer yxSort;

    /**
     * 指定批次号
     */
    private String appointValue;

    private String appointType;
}
