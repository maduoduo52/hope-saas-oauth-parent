package com.hope.saas.common.vo.wms;

import com.hope.saas.common.vo.util.BaseVo;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 移库移位实体
 *
 * @author Maduo
 * @date 2020/3/27 16:40
 */
@Data
public class StockMoveManageVo extends BaseVo {

    /**
     * 移库编号
     */
    private String parkingCode;

    /**
     * 移库时间
     */
    private Date parkingTime;

    private Long apiCusId;

    /**
     * 移库货品详情
     */
    private List<MoveGoodsDetailVo> goodsDetailList;
}
