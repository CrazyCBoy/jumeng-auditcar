package com.jumeng.auditcar.web.form;

import com.jumeng.auditcar.domain.Admin;
import com.jumeng.auditcar.domain.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ClassName MerchantForm
 * @Description TODO
 * @Author 46330
 * @Date 2019/9/12 9:55
 * @Version 1.0
 **/
@ApiModel(description = "管理员实体")
@Data
public class AdminForm extends BaseForm<Admin> {

    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名(手机号)", example="13555555555", required=true)
    private String username;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码(修改时,为空表示不修改)", example="111111", required=true)
    private String password;

    @NotBlank(message = "商户名称不能为空")
    @ApiModelProperty(value = "名称", example="郑州聚梦科技", required=true)
    private String name;

    @ApiModelProperty(value = "地址(部门)", example="九街生活广场文化路店二楼", required=false)
    private String department;

    @NotNull(message = "请选择是否启用账户")
    @ApiModelProperty(value = "是否启用", example="true", required=true)
    private Boolean isAccountEnabled;

    @NotNull(message = "请选择是否锁定账户")
    @ApiModelProperty(value = "是否锁定", example="false", required=true)
    private Boolean isAccountLocked;

    @NotNull(message = "请选择账户角色")
    @ApiModelProperty(value = "角色", required=true)
    private List<String> roleIdList;
}
