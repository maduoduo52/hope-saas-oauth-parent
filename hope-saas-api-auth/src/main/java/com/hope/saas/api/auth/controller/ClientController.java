package com.hope.saas.api.auth.controller;

import com.hope.saas.api.auth.service.ClientService;
import com.hope.saas.common.entity.util.Result;
import com.hope.saas.common.entity.wms.CustomerInfoEntity;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.web.bind.annotation.*;

/**
 * Client Controller for authorization_code
 *
 * @author Yangqi.Pang
 * @date 2020-03-29 16:41
 */
@RestController
@RequestMapping("client")
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService1) {
        this.clientService = clientService1;
    }


    @PostMapping("client")
    public Result createClient(@RequestBody CustomerInfoEntity customerInfoEntity) {
        clientService.createClient(customerInfoEntity);
        return Result.success();
    }

    @GetMapping("client")
    public Result queryClient(CustomerInfoEntity customerInfoEntity) {
        ClientDetails clientDetails = clientService.queryClient(customerInfoEntity);
        return Result.success(clientDetails);
    }

    @DeleteMapping("client")
    public Result deleteClient(@RequestBody CustomerInfoEntity customerInfoEntity) {
        clientService.deleteClient(customerInfoEntity);
        return Result.success();
    }

    @PutMapping("client")
    public Result updateClient(@RequestBody CustomerInfoEntity customerInfoEntity) {
        clientService.updateClient(customerInfoEntity);
        return Result.success();
    }


}

