package com.hope.saas.api.util.exception;

import lombok.Data;

/**
 * @author Maduo
 * @date 2020/4/14 10:12
 */
@Data
public class SecurityCheckException extends RuntimeException {

    private Integer code;

    public SecurityCheckException(String message) {
        super(message);
    }

    public SecurityCheckException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ExceptionMsg getMsg() {
        return new ExceptionMsg(getCode(), getMessage());
    }
}
