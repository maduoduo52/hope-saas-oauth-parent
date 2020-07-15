package com.hope.saas.wms.api.config.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author Maduo
 * @date 2020/3/26 16:21
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface SubmitValidate {

    /**
     * 5s 内重复提交
     *
     * @return
     */
    long times() default 5;
}
