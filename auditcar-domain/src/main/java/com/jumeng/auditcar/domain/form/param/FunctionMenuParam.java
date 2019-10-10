package com.jumeng.auditcar.domain.form.param;

import com.jumeng.auditcar.domain.reposiotry.support.DataQueryObjectPage;
import com.jumeng.auditcar.domain.reposiotry.support.QueryField;
import com.jumeng.auditcar.domain.reposiotry.support.QueryType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName FunctionMenuParam
 * @Description TODO
 * @Author 46330
 * @Date 2019/9/16 11:54
 * @Version 1.0
 **/
@ApiModel(description = "功能菜单查询条件")
@Data
public class FunctionMenuParam extends DataQueryObjectPage {

    @ApiModelProperty(value = "父级ID", example = "21932d87-51b0-45e5-a4ef-29dd3bb19a83")
    @QueryField(type = QueryType.EQUAL, name = "pid")
    private String pid;

    @ApiModelProperty(value = "类型 menu:菜单  function:功能", example = "menu")
    @QueryField(type = QueryType.EQUAL, name = "ftype")
    private String ftype;

    @ApiModelProperty(value = "归属 merchant:商户  admin:管理员  member:用户", example = "admin")
    @QueryField(type = QueryType.EQUAL, name = "fto")
    private String fto;

    @ApiModelProperty(value = "名称", example = "管理员")
    @QueryField(type = QueryType.FULL_LIKE, name = "name")
    private String name;

    @ApiModelProperty(value = "权限控制", example = "ROLE")
    @QueryField(type = QueryType.FULL_LIKE, name = "role")
    private String role;

    @ApiModelProperty(value = "完整名称", example = "管理员")
    @QueryField(type = QueryType.FULL_LIKE, name = "fullName")
    private String fullName;

    @ApiModelProperty(value = "层级", example = "1")
    @QueryField(type = QueryType.EQUAL, name = "level")
    private Integer level;

}
