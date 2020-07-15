package com.hope.saas.wms.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LoginConfig {

    @Value("${login.key}")
    public String aesKey;

    @Value("${login.time}")
    public Long time;

    @Value("${login.owner}")
    public boolean owner;

}
