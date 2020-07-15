package com.hope.saas.common.dto.wms;

import com.hope.saas.common.dto.util.BaseDTO;
import com.hope.saas.common.util.FormatLimit;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 订单入库
 *
 * @author Maduo
 * @date 2020/3/27 15:46
 */
@Data
public class StoreManageDTO extends BaseDTO {

    /**
     * 供应商
     */
    private String supplier;

    /**
     * 入库类别
     * DD：订单入库
     * TH：退货入库
     * DB：调拨入库
     * QC：期初建账
     * XN：虚拟入库
     * QT：其他入库
     */
    @NotBlank(message = "storeTypeCode不能为空")
    @FormatLimit(forages = {"DD", "TH", "DB", "QC", "XN", "QT"}, message = "storeTypeCode不符合约定")
    private String storeTypeCode;

    /**
     * 入库时间
     */
    @NotBlank(message = "storeTime不能为空")
    private String storeTime;

    /**
     * 客户订单号
     */
    @NotBlank(message = "customerOrderNo不能为空")
    @Length(max = 32, message = "customerOrderNo长度不能超过32位字符")
    private String customerOrderNo;

    /**
     * 关联门店
     */
    @Length(max = 30, message = "shopCode长度不能超过30位字符")
    private String shopCode;

    /**
     * 来源编码
     */
    private String sourceCode;

    @NotNull(message = "goodsDetailList不能为空")
    private List<ManageGoodsDetailDTO> goodsDetailList;
}
