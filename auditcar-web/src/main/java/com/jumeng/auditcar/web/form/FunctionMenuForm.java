package com.jumeng.auditcar.web.form;

import com.jumeng.auditcar.domain.FunctionMenu;
import com.jumeng.auditcar.domain.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName FunctionMenuForm
 * @Description TODO
 * @Author 46330
 * @Date 2019/9/12 9:55
 * @Version 1.0
 **/
@ApiModel(description = "功能菜单实体")
@Data
public class FunctionMenuForm extends BaseForm<FunctionMenu> {

    @ApiModelProperty(value = "父级ID(不能修改)", example="21932d87-51b0-45e5-a4ef-29dd3bb19a83", required=false)
    private String pid;

    @NotBlank(message = "类型不能为空")
    @ApiModelProperty(value = "类型 menu:菜单  function:功能", example="menu", required=true)
    private String ftype;

    @NotBlank(message = "归属不能为空")
    @ApiModelProperty(value = "归属 merchant:商户  admin:管理员  member:用户", example="admin", required=true)
    private String fto;

    @NotBlank(message = "名称不能为空")
    @ApiModelProperty(value = "名称", example="管理员列表", required=true)
    private String name;

    @NotBlank(message = "权限控制不能为空")
    @ApiModelProperty(value = "权限控制", example="ROLE_ADMIN_LIST", required=true)
    private String role;
}
