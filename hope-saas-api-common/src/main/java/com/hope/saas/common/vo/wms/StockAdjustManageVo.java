package com.hope.saas.common.vo.wms;

import com.hope.saas.common.vo.util.BaseVo;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 库存调整实体
 *
 * @author Maduo
 * @date 2020/3/27 16:32
 */
@Data
public class StockAdjustManageVo extends BaseVo {

    /**
     * 库存调整单号
     */
    private String adjustNum;

    /**
     * 调整时间
     */
    private Date adjustTime;

    private Long apiCusId;
    /**
     * 调整货品详情
     */
    private List<AdjustGoodsDetailVo> goodsDetailList;
}
