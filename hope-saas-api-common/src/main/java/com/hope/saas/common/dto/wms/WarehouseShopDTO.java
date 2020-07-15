package com.hope.saas.common.dto.wms;

import com.hope.saas.common.dto.util.BaseDTO;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author Maduo
 * @date 2020/3/27 11:11
 */
@Data
public class WarehouseShopDTO extends BaseDTO {

    /**
     * 门店编码
     */
    @NotBlank(message = "门店编码不能为空")
    @Length(max = 30, message = "shopCode长度不能超过30位字符")
    @Pattern(regexp = "^\\w{0,30}$", message = "shopCode只能由字母数字下划线组成")
    private String shopCode;

    /**
     * 门店名称
     */
    @NotBlank(message = "门店名称不能为空")
    @Length(max = 30, message = "shopName长度不能超过30位字符")
    private String shopName;

    /**
     * 负责人
     */
    private String leader;

    /**
     * 联系人
     */
    @NotBlank(message = "contact不能为空")
    private String contact;

    /**
     * 联系电话
     */
    @NotBlank(message = "phone不能为空")
    @Pattern(regexp = "^([\\d]{11})$", message = "phone只能为11位数字")
    private String phone;

    /**
     * 详细地址
     */
    @NotBlank(message = "详细地址不能为空")
    private String address;

    @NotBlank(message = "provinceName不能为空")
    private String provinceName;

    @NotBlank(message = "cityName不能为空")
    private String cityName;

    @NotBlank(message = "countyName不能为空")
    private String countyName;

    /**
     * 收货时间
     */
    private String accpetTime;

    private String pathCode;

    private String thirdShopCode;

    private Integer thirdFlag = 1;
}
