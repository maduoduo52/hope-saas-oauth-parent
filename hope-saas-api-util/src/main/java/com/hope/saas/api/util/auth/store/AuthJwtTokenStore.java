//package com.hope.saas.api.util.auth.store;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//
///**
// * 授权服务器配置类
// *
// * @author Yangqi.Pang
// * @date 2020-04-12 17:20
// */
//public class AuthJwtTokenStore {
//
//    @Bean
//    public TokenStore tokenStore(JwtAccessTokenConverter jwtAccessTokenConverter) {
//        return new JwtTokenStore(jwtAccessTokenConverter);
//    }
//
//    @Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter() {
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setSigningKey("api-wms");
//        return converter;
//    }
//
//}
