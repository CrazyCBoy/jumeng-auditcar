package com.jumeng.auditcar.domain.form.param.nopage;

import com.jumeng.auditcar.domain.reposiotry.support.DataQueryObjectSort;
import com.jumeng.auditcar.domain.reposiotry.support.QueryField;
import com.jumeng.auditcar.domain.reposiotry.support.QueryType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName MerchantRoleParam
 * @Description TODO
 * @Author 46330
 * @Date 2019/9/16 11:54
 * @Version 1.0
 **/
@ApiModel(description = "商户角色查询条件")
@Data
public class MerchantRoleNoPageParam extends DataQueryObjectSort {

    @ApiModelProperty(value = "名称", example = "财务")
    @QueryField(type = QueryType.FULL_LIKE, name = "name")
    private String name;

    @NotNull(message = "请传入是否系统内置")
    @ApiModelProperty(value = "是否系统内置", example = "true")
    @QueryField(type = QueryType.EQUAL, name = "isSystem")
    private Boolean isSystem;

}
