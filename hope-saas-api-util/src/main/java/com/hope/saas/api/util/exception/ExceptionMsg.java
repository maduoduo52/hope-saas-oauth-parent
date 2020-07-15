package com.hope.saas.api.util.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Maduo
 * @date 2020/3/24 14:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionMsg {
    private Integer code;
    private String msg;

}
