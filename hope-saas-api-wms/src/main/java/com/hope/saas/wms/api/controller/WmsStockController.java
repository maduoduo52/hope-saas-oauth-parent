package com.hope.saas.wms.api.controller;

import com.hope.saas.common.dto.wms.query.StockQueryDTO;
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
 * @date 2020/3/30 15:09
 */
@RestController
@RequestMapping("/api/wmsStock")
public class WmsStockController {

    @Autowired
    private WmsService wmsService;

    /**
     * 库存查询
     *
     * @param data 加密参数
     * @return
     */
    @PostMapping("queryStockInfo")
    @SubmitValidate
    @ReqAop(valiedArgs = true, clazz = StockQueryDTO.class, aes = true)
    public Result queryStockInfo(@RequestBody String data) {

        return wmsService.queryStockInfo(data);
    }
}
