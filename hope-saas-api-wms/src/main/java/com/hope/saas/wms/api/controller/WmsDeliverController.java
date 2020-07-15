package com.hope.saas.wms.api.controller;

import com.hope.saas.common.dto.wms.DeliverManageDTO;
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
 * @date 2020/3/30 14:31
 */
@RestController
@RequestMapping("/api/wmsDeliver")
public class WmsDeliverController {

    @Autowired
    private WmsService wmsService;

    /**
     * 出库订单
     *
     * @param data 加密参数
     * @return
     */
    @PostMapping("saveDeliver")
    @SubmitValidate
    @ReqAop(valiedArgs = true, clazz = DeliverManageDTO.class)
    public Result saveDeliver(@RequestBody String data) {

        return wmsService.saveDeliver(data);
    }
}
