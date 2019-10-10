package com.jumeng.auditcar.web.form;

import com.jumeng.auditcar.domain.PayInfo;
import com.jumeng.auditcar.domain.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;


@ApiModel(description = "支付信息实体")
@Data
public class PayInfoForm extends BaseForm<PayInfo> {
    @NotBlank(message = "用户姓名不能为空")
    @ApiModelProperty(value = "姓名", example="审车用户AAAA", required=false)
    private String username;//姓名
}
