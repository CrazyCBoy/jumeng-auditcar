package com.jumeng.auditcar.web.form;

import com.jumeng.auditcar.domain.UserInfo;
import com.jumeng.auditcar.domain.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;


@ApiModel(description = "用户登录")
@Data
public class UserLoginForm extends BaseForm<UserInfo> {
    @NotBlank(message = "用户手机号不能为空")
    @ApiModelProperty(value = "手机号", example="13555555555", required=true)
    private String mobile;// 手机号

    @ApiModelProperty(value = "短信验证码", example="596381", required=false)
    private String authcode;// 手机号

}
