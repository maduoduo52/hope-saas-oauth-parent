package com.hope.saas.wms.api.controller;

import com.hope.saas.common.dto.wms.WarehouseShopDTO;
import com.hope.saas.common.dto.wms.query.WarehouseShopQueryDTO;
import com.hope.saas.common.entity.util.Result;
import com.hope.saas.wms.api.config.annotation.ReqAop;
import com.hope.saas.wms.api.config.annotation.SubmitValidate;
import com.hope.saas.wms.api.service.api.WmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Maduo
 * @date 2020/3/30 10:26
 */
@RestController
@RequestMapping("/api/wmsShop")
public class WmsShopController {

    @Autowired
    private WmsService wmsService;

    /**
     * 门店数据保存
     *
     * @param data 加密参数
     * @return
     */
    @PostMapping("saveShop")
    @SubmitValidate
    @ReqAop(valiedArgs = true, clazz = WarehouseShopDTO.class)
    public Result saveShop(@RequestBody String data) {

        return wmsService.saveShop(data);
    }


    /**
     * 门店信息查询
     *
     * @param data 加密参数
     * @return
     */
    @PostMapping("/queryShop")
    @SubmitValidate
    @ReqAop(valiedArgs = true, clazz = WarehouseShopQueryDTO.class, aes = true)
    public Result queryShop(@RequestBody String data) {

        return wmsService.queryShop(data);
    }
}
