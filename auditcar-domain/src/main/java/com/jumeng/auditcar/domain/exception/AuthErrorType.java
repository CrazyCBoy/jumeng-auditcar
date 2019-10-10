package com.jumeng.auditcar.domain.exception;

import lombok.Getter;

@Getter
public enum AuthErrorType implements ErrorType {

    FORBIDDEN_LOGIN_FAIL("110001", "登录失败"),
    FORBIDDEN_INVOID_PASSWORD("110002", "密码错误"),
    FORBIDDEN_USER_LOCKED("110003", "用户已锁定"),
    FORBIDDEN_USER_DISABLE("110004", "用户已禁用"),
    FORBIDDEN_USER_EXPIRED("110007", "用户已过期"),
    FORBIDDEN_INVOID_USERNAME_PASSWORD("110005", "用户名或密码错误"),
    FORBIDDEN_NOT_LOGIN("100001", "用户未登录");

    /**
     * 错误类型码
     */
    private String code;
    /**
     * 错误类型描述信息
     */
    private String mesg;

    AuthErrorType(String code, String mesg) {
        this.code = code;
        this.mesg = mesg;
    }
}
