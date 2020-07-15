package com.hope.saas.api.util.exception;

import lombok.Data;

/**
 * @author Maduo
 * @date 2020/5/22 17:29
 */
@Data
public class NewHopeException extends RuntimeException {

    private Integer code;

    public NewHopeException(String message) {
        super(message);
    }

    public NewHopeException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ExceptionMsg getMsg() {
        return new ExceptionMsg(getCode(), getMessage());
    }
}
