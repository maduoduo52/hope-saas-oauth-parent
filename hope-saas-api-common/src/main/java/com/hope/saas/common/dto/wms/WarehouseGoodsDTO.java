package com.hope.saas.common.dto.wms;

import com.hope.saas.common.dto.util.BaseDTO;
import com.hope.saas.common.util.FormatLimit;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

/**
 * @author Maduo
 * @date 2020/3/27 13:14
 */
@Data
public class WarehouseGoodsDTO extends BaseDTO {

    /**
     * 货品名称
     */
    @NotBlank(message = "goodsName不能为空")
    @Length(max = 30, message = "goodsName长度不能超过30位字符")
    private String goodsName;

    /**
     * 货主货品编码
     */
    @NotBlank(message = "shipperGoodsCode不能为空")
    @Length(max = 30, message = "shipperGoodsCode长度不能超过30位字符")
    @Pattern(regexp = "^\\w{0,30}$", message = "shipperGoodsCode只能由字母数字下划线组成")
    private String shipperGoodsCode;

    /**
     * 国际编码
     */
    @Length(max = 30, message = "internationalCode长度不能超过30位字符")
    @Pattern(regexp = "^\\w{0,30}$", message = "internationalCode只能由字母数字下划线组成")
    private String internationalCode;

    /**
     * 货品类别编码
     */
    @NotBlank(message = "goodsTypeCode不能为空")
    @FormatLimit(forages = {"1", "2"}, message = "goodsTypeCode不符合约定")
    private String goodsTypeCode;

    /**
     * 货品规格
     */
    @NotBlank(message = "goodsSpecifications不能为空")
    @Length(max = 30, message = "goodsSpecifications长度不能超过30位字符")
    private String goodsSpecifications;

    /**
     * 整单位
     */
    @NotBlank(message = "bigUnit不能为空")
    @Length(max = 10, message = "bigUnit长度不能超过10位字符")
    private String bigUnit;

    /**
     * 换算率
     */
    @NotBlank(message = "conversionRate不能为空")
    @Min(value = 1, message = "conversionRate最小值为1")
    private BigDecimal conversionRate;

    /**
     * 零单位
     */
    @NotBlank(message = "smallUnit不能为空")
    @Length(max = 10, message = "smallUnit长度不能超过10位字符")
    private String smallUnit;

    /**
     * 默认单位
     */
    @NotBlank(message = "defaultUnit不能为空")
    @Length(max = 10, message = "defaultUnit长度不能超过10位字符")
    private String defaultUnit;

    /**
     * 件重(千克)
     */
    @NotBlank(message = "weight不能为空")
    @Min(value = 0, message = "weight最小值为0")
    private BigDecimal weight;

    /**
     * 计费件重(千克)
     */
    @Min(value = 0, message = "chargeWeight最小值为0")
    private BigDecimal chargeWeight;

    /**
     * 单板数量
     */
    @NotBlank(message = "veneerWeight不能为空")
    @Min(value = 1, message = "veneerWeight最小值为1")
    private BigDecimal veneerWeight;

    /**
     * 长（厘米）
     */
    @NotBlank(message = "goodsLength不能为空")
    @Min(value = 0, message = "goodsLength最小值为0")
    private BigDecimal goodsLength;

    /**
     * 宽（厘米）
     */
    @NotBlank(message = "goodsWide不能为空")
    @Min(value = 0, message = "goodsWide最小值为0")
    private BigDecimal goodsWide;

    /**
     * 高（厘米）
     */
    @NotBlank(message = "goodsHight不能为空")
    @Min(value = 0, message = "goodsHight最小值为0")
    private BigDecimal goodsHight;

    /**
     * 温层编码
     */
    @NotBlank(message = "temperatureLayerCode不能为空")
    @FormatLimit(forages = {"1", "2", "3", "4"}, message = "temperatureLayerCode不符合约定")
    private String temperatureLayerCode;

    /**
     * 单价（元/件）
     */
    @Min(value = 0, message = "goodsPrice最小值为0")
    private BigDecimal goodsPrice;

    /**
     * 保质期（天）
     */
    @NotBlank(message = "shelfLife不能为空")
    @Min(value = 0, message = "shelfLife最小值为0")
    private BigDecimal shelfLife;

    /**
     * 临期天数
     */
    @Min(value = 0, message = "conversionRate最小值为0")
    private BigDecimal daysDue;

    /**
     * 安全库存
     */
    @Min(value = 0, message = "safeStock最小值为0")
    private BigDecimal safeStock;

    /**
     * 包装类型
     */
    @NotBlank(message = "weight不能为空")
    @Length(max = 30, message = "packagTypeName长度不能超过30位字符")
    private String packagTypeName;

    /**
     * 是否抄码
     */
    @NotBlank(message = "isCopyCode不能为空")
    @FormatLimit(forages = {"0", "1"}, message = "isCopyCode不符合约定")
    private Integer isCopyCode;

    /**
     * 批次属性
     */
    @NotBlank(message = "batchAttributeName不能为空")
    @Length(max = 30, message = "batchAttributeName长度不能超过30位字符")
    @FormatLimit(forages = {"生产日期+入库日期+三位自增码", "生产日期", "生产日期+入库日期", "到期日期", "生产日期+到期日期"}, message = "batchAttributeName不符合约定")
    private String batchAttributeName;

    /**
     * 允收期
     */
    @Min(value = 1, message = "dueInDays最小值为1")
    private BigDecimal dueInDays;

    /**
     * 允出期
     */
    @Min(value = 1, message = "dueOutDays最小值为1")
    private BigDecimal dueOutDays;

    /**
     * 辅助单位
     */
    private String auxiliaryUnits;

    /**
     * 是否越库
     */
    @NotBlank(message = "isOverWarehouse不能为空")
    @FormatLimit(forages = {"0", "1"}, message = "isOverWarehouse不符合约定")
    private Integer isOverWarehouse;

    /**
     * 越库是否分拣
     */
    @FormatLimit(forages = {"0", "1"}, message = "isOverWarehousePick不符合约定")
    private Integer isOverWarehousePick;

    /**
     * 解冻标签
     */
    @FormatLimit(forages = {"0", "1"}, message = "isUnfreeze不符合约定")
    private Integer isUnfreeze;

    /**
     * 是否装卸
     */
    @FormatLimit(forages = {"0", "1"}, message = "isLoad不符合约定")
    private Integer isLoad;

    /**
     * 一托几品
     */
    @Length(max = 10, message = "blockGoodsNum长度不能超过10位字符")
    @Min(value = 1, message = "blockGoodsNum最小值为1")
    private Integer blockGoodsNum;

    /**
     * 第三方货品编码
     */
    @Length(max = 50, message = "thirdProCode长度不能超过50位字符")
    private String thirdProCode;

    private String partnerCode;

    private String partnerName;

}
