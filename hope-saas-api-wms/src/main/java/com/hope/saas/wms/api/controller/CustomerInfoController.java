package com.hope.saas.wms.api.controller;

import com.alibaba.fastjson.JSON;
import com.hope.saas.api.util.util.AESCodeUtil;
import com.hope.saas.common.dto.wms.CustomerInfoDto;
import com.hope.saas.common.entity.util.Result;
import com.hope.saas.common.entity.wms.CustomerInfoEntity;
import com.hope.saas.common.enums.ResultCodeEnum;
import com.hope.saas.wms.api.config.annotation.IgnoreLogin;
import com.hope.saas.wms.api.config.annotation.ReqAop;
import com.hope.saas.wms.api.config.annotation.SubmitValidate;
import com.hope.saas.wms.api.config.rabbit.RabbitConstant;
import com.hope.saas.wms.api.service.CustomerInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 用户信息表
 *
 * @author Maduo
 * @date 2020-03-20 14:09:44
 */
@Slf4j
@RestController
@RequestMapping(value = "customerInfo")
public class CustomerInfoController {

    @Autowired
    private CustomerInfoService customerInfoService;

    @PostMapping("/customerRegister")
    @SubmitValidate
    @IgnoreLogin
    public Result saveCustomer(@Valid @RequestBody CustomerInfoDto customerInfoDto) {
        return customerInfoService.saveCustomer(customerInfoDto);
    }

    @GetMapping("queryById")
    @ReqAop(aes = true)
    @SubmitValidate
    public Result queryById(Long id) {
        CustomerInfoEntity infoEntity = customerInfoService.selectById(id);
        return Result.success(infoEntity);
    }

    @RabbitListener(queues = RabbitConstant.WMS_API_INTERFACE)
    @RabbitHandler
    public void listenTest(String message) {
        log.info("===>开始处理队列消息:{}", message);
    }


    @PostMapping("encodeData")
    @SubmitValidate
    @IgnoreLogin
    public Result encodeData(String aesKey, @RequestBody Object data) {
        String encode = AESCodeUtil.encode(JSON.toJSONString(data), aesKey);

        return Result.success(encode, ResultCodeEnum.SUCCESS.getDesc());
    }

    @PostMapping("decodeData")
    @SubmitValidate
    @IgnoreLogin
    public Result decodeData(String aesKey, @RequestBody Object data) {
        String encode = AESCodeUtil.decode(JSON.toJSONString(data), aesKey);

        return Result.success(encode, ResultCodeEnum.SUCCESS.getDesc());
    }

}