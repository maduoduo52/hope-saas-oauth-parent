package com.hope.saas.api.util.exception;

import lombok.Data;

/**
 * @author Maduo
 * @date 2020/4/7 15:51
 */
@Data
public class ParamsFormatException extends RuntimeException {

    private Integer code;

    public ParamsFormatException(String message) {
        super(message);
    }

    public ParamsFormatException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ParamsFormatException() {
    }

    public ExceptionMsg getMsg() {
        return new ExceptionMsg(getCode(), getMessage());
    }
}
