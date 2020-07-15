package com.hope.saas.common.dto.wms.query;

import com.hope.saas.common.dto.util.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Maduo
 * @date 2020/4/15 10:47
 */
@Data
public class StoreDeliverDataQueryDTO extends BaseDTO {

    /**
     * 查询类型
     * STORE:入库订单
     * DELIVER:出库单
     */
    @NotBlank(message = "queryType不能为空")
    private String queryType;

    /**
     * 查询开始时间
     */
    @NotNull(message = "startTime不能为空")
    private Date startTime;

    /**
     * 查询结束时间
     */
    @NotNull(message = "endTime不能为空")
    private Date endTime;

    /**
     * 货品名称
     */
    private String goodsName;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 货主货品编码
     */
    private String shipperGoodsCode;
}
