package com.hope.saas.common.dto.wms;

import com.hope.saas.common.dto.util.BaseDTO;
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
public class StockAdjustManageDTO extends BaseDTO {

    /**
     * 库存调整单号
     */
    private String adjustNum;

    /**
     * 调整时间
     */
    private Date adjustTime;

    /**
     * 调整货品详情
     */
    private List<AdjustGoodsDetailDTO> goodsDetailList;
}
