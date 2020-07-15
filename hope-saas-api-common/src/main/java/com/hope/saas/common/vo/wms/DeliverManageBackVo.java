package com.hope.saas.common.vo.wms;

import com.hope.saas.common.vo.util.BaseVo;
import lombok.Data;

import java.util.List;

/**
 * 出库数据反馈实体
 *
 * @author Maduo
 * @date 2020/3/27 16:12
 */
@Data
public class DeliverManageBackVo extends BaseVo {

    /**
     * 出库确认时间
     */
    private String deliverTime;

    /**
     * 出库单编号
     */
    private String deliverNo;

    /**
     * 客户订单号
     */
    private String customerOrderNo;

    private Long apiCusId;

    /**
     * 入库货品详情
     */
    private List<DeliverBackGoodsDetailVo> goodsDetailList;
}
