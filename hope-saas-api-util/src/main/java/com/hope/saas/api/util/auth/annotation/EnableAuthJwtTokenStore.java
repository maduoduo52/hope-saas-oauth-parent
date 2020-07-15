package com.hope.saas.api.util.auth.annotation;

//import com.hope.saas.api.util.auth.store.AuthJwtTokenStore;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 开启 JWT 令牌存储（授权服务器-非对称加密）
 *
 * @author Yangqi.Pang
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
//@Import(AuthJwtTokenStore.class)
public @interface EnableAuthJwtTokenStore {
}
