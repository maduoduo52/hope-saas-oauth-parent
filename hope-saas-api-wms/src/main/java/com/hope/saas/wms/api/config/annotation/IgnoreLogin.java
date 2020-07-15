package com.hope.saas.wms.api.config.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author Maduo
 * @date 2020/4/15 18:02
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface IgnoreLogin {
}
