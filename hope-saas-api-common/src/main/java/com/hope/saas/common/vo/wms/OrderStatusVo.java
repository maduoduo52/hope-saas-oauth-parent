package com.hope.saas.common.vo.wms;

import com.hope.saas.common.vo.util.BaseVo;
import lombok.Data;

/**
 * 出入库单状态查询
 *
 * @author Maduo
 * @date 2020/3/27 16:50
 */
@Data
public class OrderStatusVo extends BaseVo {

    /**
     * 客户订单号
     */
    private String customerOrderNo;

    /**
     * 订单状态
     * 0:草稿
     * 非0：不允许删除
     */
    private Integer status;
}
