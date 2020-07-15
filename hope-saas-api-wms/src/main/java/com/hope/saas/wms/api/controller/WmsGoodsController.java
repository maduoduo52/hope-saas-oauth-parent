package com.hope.saas.wms.api.controller;

import com.hope.saas.common.dto.wms.WarehouseGoodsDTO;
import com.hope.saas.common.dto.wms.WarehouseGoodsModifyDTO;
import com.hope.saas.common.dto.wms.query.WarehouseGoodsQueryDTO;
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
 * @date 2020/3/30 14:19
 */
@RestController
@RequestMapping("/api/wmsGoods")
public class WmsGoodsController {

    @Autowired
    private WmsService wmsService;

    /**
     * 保存货品信息
     *
     * @param data 加密参数
     * @return
     */
    @PostMapping("saveGoods")
    @SubmitValidate
    @ReqAop(valiedArgs = true, clazz = WarehouseGoodsDTO.class)
    public Result saveGoods(@RequestBody String data) {

        return wmsService.saveGoods(data);
    }

    /**
     * 修改货品信息
     *
     * @param data
     * @return
     */
    @PostMapping("modifyGoods")
    @SubmitValidate
    @ReqAop(valiedArgs = true, clazz = WarehouseGoodsModifyDTO.class)
    public Result modifyGoods(@RequestBody String data) {

        return wmsService.modifyGoods(data);
    }

    /**
     * 货品信息查询
     *
     * @param data 加密参数
     * @return
     */
    @PostMapping("/queryGoods")
    @SubmitValidate
    @ReqAop(valiedArgs = true, clazz = WarehouseGoodsQueryDTO.class, aes = true)
    public Result queryGoods(@RequestBody String data) {

        return wmsService.queryGoods(data);
    }
}
