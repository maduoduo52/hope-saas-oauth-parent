package com.hope.saas.api.util.auth.enhancer;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yangqi.Pang
 * @date 2020-04-12 21:44
 */

@Configuration
public class CustomTokenEnhancer implements TokenEnhancer {

    private RedisTemplate<Object, Object> redisTemplate;

    public CustomTokenEnhancer(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        String clientId = (String) oAuth2Authentication.getPrincipal();
        final Map<String, Object> additionalInfo = new HashMap<>(16);
        additionalInfo.put("customInfo", redisTemplate.opsForValue().get(clientId));
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(additionalInfo);
        return oAuth2AccessToken;
    }
}
