package com.hope.saas.common.dto.util;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author Maduo
 * @date 2020/3/20 13:45
 */
@Data
public class BaseDTO {

    @NotBlank(message = "tenantId不能为空")
    @Length(max = 32, message = "tenantId长度不能超过32位字符")
    private String tenantId;

    @NotBlank(message = "shipperCode不能为空")
    @Length(max = 30, message = "tenantId长度不能超过30位字符")
    private String shipperCode;

}
