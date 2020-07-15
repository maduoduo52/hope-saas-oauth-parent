package com.hope.saas.common.dto.wms.query;

import com.hope.saas.common.dto.util.PageDTO;
import lombok.Data;

/**
 * @author Maduo
 * @date 2020/3/27 13:25
 */
@Data
public class WarehouseGoodsQueryDTO extends PageDTO {

    /**
     * 货品名称
     */
    private String goodsName;

    /**
     * 货主货品编码
     */
    private String shipperGoodsCode;

    /**
     * 第三方货品编码
     */
    private String thirdProCode;
}
