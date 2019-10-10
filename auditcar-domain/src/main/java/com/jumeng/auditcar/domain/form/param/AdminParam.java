package com.jumeng.auditcar.domain.form.param;

import com.jumeng.auditcar.domain.reposiotry.support.DataQueryObjectPage;
import com.jumeng.auditcar.domain.reposiotry.support.QueryField;
import com.jumeng.auditcar.domain.reposiotry.support.QueryType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName AdminParam
 * @Description TODO
 * @Author 46330
 * @Date 2019/9/16 11:54
 * @Version 1.0
 **/
@ApiModel(description = "管理员查询条件")
@Data
public class AdminParam extends DataQueryObjectPage {

    @ApiModelProperty(value = "名称", example = "张三")
    @QueryField(type = QueryType.FULL_LIKE, name = "name")
    private String name;


    @ApiModelProperty(value = "用户名", example = "13523448667")
    @QueryField(type = QueryType.EQUAL, name = "username")
    private String username;
}
