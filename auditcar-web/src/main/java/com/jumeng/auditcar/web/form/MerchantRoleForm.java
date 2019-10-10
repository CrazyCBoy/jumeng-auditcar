package com.jumeng.auditcar.web.form;

import com.jumeng.auditcar.domain.MerchantRole;
import com.jumeng.auditcar.domain.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ClassName MerchantRoleForm
 * @Description TODO
 * @Author 46330
 * @Date 2019/9/12 9:55
 * @Version 1.0
 **/
@ApiModel(description = "商户角色实体")
@Data
public class MerchantRoleForm extends BaseForm<MerchantRole> {

    @NotBlank(message = "角色名称不能为空")
    @ApiModelProperty(value = "角色名称", example="财务", required=true)
    private String name;
    @ApiModelProperty(value = "角色描述", example="财务人员专属角色", required=false)
    private String description;
    @NotNull(message = "请选择当前角色拥有的权限")
    @ApiModelProperty(value = "权限", required=true)
    private List<String> authorityList;// 权限集合ID存储
    @ApiModelProperty(value = "商户ID", example = "21932d87-51b0-45e5-a4ef-29dd3bb19a83", required=true)
    private String merchantId; //商户ID
}
