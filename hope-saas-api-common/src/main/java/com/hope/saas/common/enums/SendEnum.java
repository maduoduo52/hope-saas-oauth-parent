package com.hope.saas.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Maduo
 * @date 2020/4/24 16:02
 */
@Getter
@AllArgsConstructor
public enum SendEnum {

    NOT_NEED_SEND("NOT_NEED_SEND", "不需要发送"),

    ;

    private String code;

    private String message;
}
