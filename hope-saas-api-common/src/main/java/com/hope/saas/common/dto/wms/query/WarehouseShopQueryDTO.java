package com.hope.saas.common.dto.wms.query;

import com.hope.saas.common.dto.util.PageDTO;
import lombok.Data;

/**
 * 货品信息查询
 *
 * @author Maduo
 * @date 2020/3/27 11:26
 */
@Data
public class WarehouseShopQueryDTO extends PageDTO {

    /**
     * 门店编码
     */
    private String shopCode;

    /**
     * 门店名称
     */
    private String shopName;
}
