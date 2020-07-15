package com.hope.saas.common.dto.wms.query;

import com.hope.saas.common.dto.util.PageDTO;
import lombok.Data;

import java.util.Date;

/**
 * @author Maduo
 * @date 2020/3/27 16:14
 */
@Data
public class StockQueryDTO extends PageDTO {

    /**
     * 货品名称
     */
    private String goodsName;

    /**
     * 入库批次号
     */
    private String incomingBatch;

    /**
     *
     */
    private String locationCode;

    /**
     * 货主货品编码
     */
    private String shipperGoodsCode;

    /**
     * 温层编码
     */
    private String temperatureLayerCode;

    /**
     * 查询时间
     */
    private Date queryTime;
}
