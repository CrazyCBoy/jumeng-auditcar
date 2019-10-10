package com.jumeng.auditcar.domain.exception;

import lombok.Getter;

@Getter
public enum AdminErrorType implements ErrorType {

    INVOID_DATA_ID("210001", "数据不存在"),
    INVOID_DATA_EXIST("210002", "数据已存在"),
    INVOID_PARAM_USER_EXIST("220003", "用户已存在")
    ;

    /**
     * 错误类型码
     */
    private String code;
    /**
     * 错误类型描述信息
     */
    private String mesg;

    AdminErrorType(String code, String mesg) {
        this.code = code;
        this.mesg = mesg;
    }
}
