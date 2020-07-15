package com.hope.saas.common.dto.wms.query;

import com.hope.saas.common.dto.util.BaseDTO;
import com.hope.saas.common.util.FormatLimit;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 出入库单状态查询
 *
 * @author Maduo
 * @date 2020/3/27 15:58
 */
@Data
public class OrderQueryDTO extends BaseDTO {

    /**
     * 订单类别
     * STORE:入库订单
     * DELIVER:出库订单
     */
    @NotBlank(message = "orderType不能为空")
    @FormatLimit(forages = {"STORE", "DELIVER"}, message = "orderType不符合约定")
    private String orderType;

    /**
     * 客户订单号
     */
    @NotBlank(message = "customerOrderNo不能为空")
    private String customerOrderNo;

    /**
     * 订单状态
     * 0:草稿
     * 非0：不允许删除
     */
    private Integer status;
}
