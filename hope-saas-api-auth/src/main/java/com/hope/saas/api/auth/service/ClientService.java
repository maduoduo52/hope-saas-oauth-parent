package com.hope.saas.api.auth.service;

import com.hope.saas.common.entity.wms.CustomerInfoEntity;
import org.springframework.security.oauth2.provider.ClientDetails;

/**
 * ClientService
 *
 * @author Yangqi.Pang
 */
public interface ClientService {

    /**
     * createClient
     *
     * @param customerInfoEntity customerInfoEntity
     */
    void createClient(CustomerInfoEntity customerInfoEntity);

    /**
     * queryClient
     *
     * @param customerInfoEntity customerInfoEntity
     * @return ClientDetails
     */
    ClientDetails queryClient(CustomerInfoEntity customerInfoEntity);

    /**
     * deleteClient
     *
     * @param customerInfoEntity customerInfoEntity
     */
    void deleteClient(CustomerInfoEntity customerInfoEntity);

    /**
     * updateClient
     *
     * @param customerInfoEntity customerInfoEntity
     */
    void updateClient(CustomerInfoEntity customerInfoEntity);
}
