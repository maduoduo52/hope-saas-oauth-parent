package com.hope.saas.api.util.exception;

import lombok.Data;

/**
 * @author Maduo
 * @date 2020/3/26 18:27
 */
@Data
public class ResubmitException extends RuntimeException {

    private Integer code;

    public ResubmitException(String message) {
        super(message);
    }

    public ResubmitException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ExceptionMsg getMsg() {
        return new ExceptionMsg(getCode(), getMessage());
    }
}
