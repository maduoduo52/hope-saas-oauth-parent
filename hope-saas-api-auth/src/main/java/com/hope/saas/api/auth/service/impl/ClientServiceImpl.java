package com.hope.saas.api.auth.service.impl;

import com.hope.saas.api.auth.service.ClientService;
import com.hope.saas.common.entity.wms.CustomerInfoEntity;
import com.hope.saas.common.enums.AuthoritiesEnum;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

/**
 * @author Yangqi.Pang
 * @date 2020-03-31 13:59
 */
@Service
public class ClientServiceImpl implements ClientService {

    private JdbcClientDetailsService jdbcClientDetailsService;

    private PasswordEncoder passwordEncoder;

    private RedisTemplate<Object, Object> redisTemplate;

    public ClientServiceImpl(@Qualifier("dataSource") DataSource dataSource,
                             PasswordEncoder passwordEncoder1,
                             RedisTemplate<Object, Object> redisTemplate) {
        this.jdbcClientDetailsService = new JdbcClientDetailsService(dataSource);
        this.passwordEncoder = passwordEncoder1;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void createClient(CustomerInfoEntity customerInfoEntity) {
        String[] authorizedGrantTypes = {"client_credentials", "refresh_token"};
        String clientId = getClientId(customerInfoEntity);
        String clientSecret = passwordEncoder.encode(customerInfoEntity.getPassword());
        BaseClientDetails clientDetails = new BaseClientDetails();
        clientDetails.setClientId(clientId);
        clientDetails.setClientSecret(clientSecret);
        clientDetails.setAuthorizedGrantTypes(Arrays.asList(authorizedGrantTypes));
        clientDetails.setScope(Collections.singletonList("all"));
        clientDetails.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(AuthoritiesEnum.USER.getRole()));
        clientDetails.setRefreshTokenValiditySeconds(7200);
        clientDetails.setAccessTokenValiditySeconds(7200);
        jdbcClientDetailsService.addClientDetails(clientDetails);
        redisTemplate.opsForValue().set(clientId, customerInfoEntity);
    }

    @Override
    public ClientDetails queryClient(CustomerInfoEntity customerInfoEntity) {
        String clientId = getClientId(customerInfoEntity);
        return jdbcClientDetailsService.loadClientByClientId(clientId);
    }

    @Override
    public void deleteClient(CustomerInfoEntity customerInfoEntity) {
        String clientId = getClientId(customerInfoEntity);
        jdbcClientDetailsService.removeClientDetails(clientId);
        redisTemplate.delete(clientId);
    }

    @Override
    public void updateClient(CustomerInfoEntity customerInfoEntity) {
        String clientId = getClientId(customerInfoEntity);
        String clientSecret = passwordEncoder.encode(customerInfoEntity.getPassword());
        jdbcClientDetailsService.updateClientSecret(clientId, clientSecret);
        redisTemplate.opsForValue().set(clientId, customerInfoEntity);
    }

    /**
     * getClientId
     *
     * @param customerInfoEntity customerInfoEntity
     * @return String clientId
     */
    private String getClientId(CustomerInfoEntity customerInfoEntity) {
        return UUID.nameUUIDFromBytes((customerInfoEntity.getUserName()
                + customerInfoEntity.getTenantId()
        ).getBytes(StandardCharsets.UTF_8)).toString().replace("-", "");
    }

}
