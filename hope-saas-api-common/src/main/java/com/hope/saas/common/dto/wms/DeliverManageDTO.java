package com.hope.saas.common.dto.wms;

import com.hope.saas.common.dto.util.BaseDTO;
import com.hope.saas.common.util.FormatLimit;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 订单出库
 *
 * @author Maduo
 * @date 2020/3/27 15:52
 */
@Data
public class DeliverManageDTO extends BaseDTO {

    /**
     * 出库类别
     * DD：订单出库
     * TH：退供出库
     * DB：调拨出库
     * XH：销毁出库
     * QT：其他出库
     */
    @NotBlank(message = "deliverTypeCode不能为空")
    @FormatLimit(forages = {"DD", "TH", "DB", "XH", "QT"}, message = "deliverTypeCode不符合约定")
    private String deliverTypeCode;

    /**
     * 出库时间
     */
    @NotBlank(message = "deliverTime不能为空")
    private String deliverTime;

    /**
     * 客户订单号
     */
    @NotBlank(message = "customerOrderNo不能为空")
    @Length(max = 32, message = "customerOrderNo长度不能超过32位字符")
    private String customerOrderNo;

    /**
     * 门店编码
     */
    @NotBlank(message = "shopCode不能为空")
    @Length(max = 30, message = "shopCode长度不能超过30位字符")
    private String shopCode;

    /**
     * 线路编码
     */
    @Length(max = 32, message = "pathCode长度不能超过32位字符")
    private String pathCode;

    /**
     * 备注
     */
    private String note;

    /**
     * 是否仓配一体
     */
    @FormatLimit(forages = {"0", "1"}, message = "warehouseAllocation不符合约定")
    private Integer warehouseAllocation;

    /**
     * 供应商地址
     */
    private String supplierAddress;

    /**
     * 供应商联系人
     */
    private String supplierContact;

    /**
     * 供应商电话
     */
    private String supplierPhone;

    /**
     * 来源编码
     */
    private String sourceCode;

    @NotNull(message = "goodsDetailList不能为空")
    private List<ManageGoodsDetailDTO> goodsDetailList;
}
