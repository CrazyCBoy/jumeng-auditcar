package com.jumeng.auditcar.domain.exception;

import lombok.Getter;

@Getter
public enum UserErrorType implements ErrorType {

    FAIL_GET_ALI_USER_ID("310001", "获取支付宝用户ID失败,请稍后再试"),
    ;

    /**
     * 错误类型码
     */
    private String code;
    /**
     * 错误类型描述信息
     */
    private String mesg;

    UserErrorType(String code, String mesg) {
        this.code = code;
        this.mesg = mesg;
    }
}
