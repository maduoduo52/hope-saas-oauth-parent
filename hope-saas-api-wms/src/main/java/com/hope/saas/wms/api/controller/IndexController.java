package com.hope.saas.wms.api.controller;

import com.hope.saas.wms.api.config.annotation.IgnoreLogin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Maduo
 * @date 2020/4/22 14:19
 */
@Controller
public class IndexController {

    @GetMapping("index.html")
    @IgnoreLogin
    public String index() {
        return "index";
    }
}
