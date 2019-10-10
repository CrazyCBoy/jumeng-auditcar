package com.jumeng.auditcar.web.form;

import com.jumeng.auditcar.domain.OrderInfo;
import com.jumeng.auditcar.domain.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;


@ApiModel(description = "订单信息实体")
@Data
public class OrderInfoForm extends BaseForm<OrderInfo> {

    @NotBlank(message = "用户编号不能为空")
    @ApiModelProperty(value = "用户编号", example="13", required=false)
    private String uid;//姓名
}
