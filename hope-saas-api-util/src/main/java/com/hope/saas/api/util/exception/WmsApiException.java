package com.hope.saas.api.util.exception;

import lombok.Data;

/**
 * @author Maduo
 * @date 2020/3/24 14:53
 */
@Data
public class WmsApiException extends RuntimeException {

    private Integer code;

    public WmsApiException(String message) {
        super(message);
    }

    public WmsApiException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ExceptionMsg getMsg() {
        return new ExceptionMsg(getCode(), getMessage());
    }
}
