package com.hope.saas.common.dto.wms;

import com.hope.saas.common.dto.util.BaseDTO;
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
public class StockMoveManageDTO extends BaseDTO {

    /**
     * 移库编号
     */
    private String parkingCode;

    /**
     * 移库时间
     */
    private Date parkingTime;

    /**
     * 移库货品详情
     */
    private List<MoveGoodsDetailDTO> goodsDetailList;
}
