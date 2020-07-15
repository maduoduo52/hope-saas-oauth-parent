package com.hope.saas.wms.api.config.mybatisplus.uitl;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author Maduo
 * @date 2020/3/20 13:33
 */
@Data
public class EwGenJavaBD {

    private String name;

    private String mark;

    private List<Map<String, String>> fileds;
}
