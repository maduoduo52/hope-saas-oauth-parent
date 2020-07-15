package com.hope.saas.common.vo.wms;

import com.hope.saas.common.vo.util.BaseVo;
import lombok.Data;

import java.util.List;

/**
 * 入库数据反馈实体对象
 *
 * @author Maduo
 * @date 2020/3/27 16:09
 */
@Data
public class StoreManageBackVo extends BaseVo {

    /**
     * 上架时间
     */
    private String upperShelfTime;

    /**
     * 入库单编号
     */
    private String storeNo;

    /**
     * 客户订单号
     */
    private String customerOrderNo;

    /**
     * 入库时间
     */
    private String storeTime;

    private Long apiCusId;

    /**
     * 入库货品详情
     */
    private List<StoreBackGoodsDetailVo> goodsDetailList;
}
