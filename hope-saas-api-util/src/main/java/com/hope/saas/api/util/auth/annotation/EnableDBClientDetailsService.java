package com.hope.saas.api.util.auth.annotation;

import com.hope.saas.api.util.auth.service.DbClientDetailsService;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * EnableDBClientDetailsService
 *
 * @author Yangqi.Pang
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(DbClientDetailsService.class)
public @interface EnableDBClientDetailsService {
}
