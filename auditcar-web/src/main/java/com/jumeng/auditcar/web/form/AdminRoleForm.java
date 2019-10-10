package com.jumeng.auditcar.web.form;

import com.jumeng.auditcar.domain.AdminRole;
import com.jumeng.auditcar.domain.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @ClassName AdminRoleForm
 * @Description TODO
 * @Author 46330
 * @Date 2019/9/12 9:55
 * @Version 1.0
 **/
@ApiModel(description = "管理员角色实体")
@Data
public class AdminRoleForm extends BaseForm<AdminRole> {

    @NotBlank(message = "角色名称不能为空")
    @ApiModelProperty(value = "角色名称", example="财务", required=true)
    private String name;
    @ApiModelProperty(value = "角色描述", example="财务人员专属角色", required=false)
    private String description;
    @NotBlank(message = "请选择当前角色拥有的权限")
    @ApiModelProperty(value = "权限", required=true)
    private List<String> authorityList;// 权限集合ID存储
}
