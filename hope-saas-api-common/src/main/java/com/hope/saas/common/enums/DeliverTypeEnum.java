package com.hope.saas.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 出库类别枚举类
 *
 * @author Yangqi.Pang
 */
@Getter
@AllArgsConstructor
public enum DeliverTypeEnum {

    /**
     * 订单出库
     */
    DD("DD", "订单出库"),

    /**
     * 退供出库
     */
    TH("TH", "退供出库"),

    /**
     * 调拨出库
     */
    DB("DB", "调拨出库"),

    /**
     * 销毁出库
     */
    XH("XH", "销毁出库"),

    /**
     * 其它出库
     */
    QT("QT", "其它出库"),

    /**
     * 零担出库
     */
    LD("LD", "零担出库");

    private final String code;
    private final String typeName;
}
