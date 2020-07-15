package com.hope.saas.common.constants;

import com.hope.saas.common.entity.wms.CustomerInfoEntity;
import lombok.Data;

/**
 * @author Maduo
 * @date 2020/4/15 18:02
 */
@Data
public final class HeaderDto {

    /**
     * 用户信息
     */
    private CustomerInfoEntity customerInfoEntity;

    private String token;

    private Long postId;

    private HeaderDto() {
    }

    /**
     * 初始化
     *
     * @return
     */
    public static HeaderDto initEmp(CustomerInfoEntity customerInfoEntity) {
        HeaderDto herderDto = new HeaderDto();
        herderDto.setCustomerInfoEntity(customerInfoEntity);
        return herderDto;
    }

    /**
     * 初始化
     *
     * @return
     */
    public static HeaderDto initWebEmp(Long postId, CustomerInfoEntity customerInfoEntity) {
        HeaderDto herderDto = new HeaderDto();
        herderDto.setPostId(postId);
        herderDto.setCustomerInfoEntity(customerInfoEntity);
        return herderDto;
    }
}
