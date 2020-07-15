package com.hope.saas.common.vo.wms;

import com.hope.saas.common.vo.util.BaseVo;
import lombok.Data;

/**
 * 门店查询
 *
 * @author Maduo
 * @date 2020/3/27 11:11
 */
@Data
public class WarehouseShopVo extends BaseVo {

    /**
     * 门店编码
     */
    private String shopCode;

    /**
     * 门店名称
     */
    private String shopName;

    /**
     * 负责人
     */
    private String leader;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 收货时间
     */
    private String accpetTime;
}
