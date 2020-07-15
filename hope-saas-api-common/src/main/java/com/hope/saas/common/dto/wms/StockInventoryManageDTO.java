package com.hope.saas.common.dto.wms;

import com.hope.saas.common.dto.util.BaseDTO;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 库存盘点实体
 *
 * @author Maduo
 * @date 2020/3/27 16:37
 */
@Data
public class StockInventoryManageDTO extends BaseDTO {

    /**
     * 库存盘点单号
     */
    private String inventoryNum;

    /**
     * 盘点时间
     */
    private Date inventoryTime;

    /**
     * 盘点货品详情
     */
    private List<InventoryGoodsDetailDTO> goodsDetailList;
}
