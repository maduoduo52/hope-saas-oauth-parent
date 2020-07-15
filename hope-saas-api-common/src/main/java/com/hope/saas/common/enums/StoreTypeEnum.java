package com.hope.saas.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * @author Maduo
 * @date 2020/5/27 11:08
 */
@Getter
@AllArgsConstructor
public enum StoreTypeEnum {

    /**
     * 订单入库
     */
    DD("DD", "订单入库"),

    /**
     * 退货入库
     */
    TH("TH", "退货入库"),

    /**
     * 调拨入库
     */
    DB("DB", "调拨入库"),

    /**
     * 期初建账
     */
    QC("QC", "期初建账"),

    /**
     * 其它入库
     */
    QT("QT", "其它入库"),

    /**
     * 零担入库
     */
    LD("LD", "零担入库"),
    /**
     * 虚拟入库
     */
    XN("XN", "虚拟入库");

    private String code;
    private String typeName;
}
