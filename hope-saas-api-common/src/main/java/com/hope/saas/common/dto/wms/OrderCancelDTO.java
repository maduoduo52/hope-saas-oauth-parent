package com.hope.saas.common.dto.wms;

import com.hope.saas.common.dto.wms.query.OrderQueryDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Maduo
 * @date 2020/3/27 16:01
 */
@Data
public class OrderCancelDTO extends OrderQueryDTO {

    /**
     * 操作类型
     * 0：取消
     * 1：删除
     */
    @NotNull(message = "operaType不能为空")
    private Integer operaType;

    /**
     * 备注
     */
    private String remark;
}
