package com.jumeng.auditcar.domain.form.param;

import com.jumeng.auditcar.domain.reposiotry.support.DataQueryObjectPage;
import com.jumeng.auditcar.domain.reposiotry.support.QueryField;
import com.jumeng.auditcar.domain.reposiotry.support.QueryType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName UserParam
 * @Description TODO
 * @Author 46330
 * @Date 2019/9/16 11:54
 * @Version 1.0
 **/
@ApiModel(description = "用户基础信息查询条件")
@Data
public class UserParam extends DataQueryObjectPage {

    @ApiModelProperty(value = "姓名", example = "审车用户A")
    @QueryField(type = QueryType.FULL_LIKE, name = "name")
    private String name;

    @ApiModelProperty(value = "手机号", example = "13555555555")
    @QueryField(type = QueryType.EQUAL, name = "mobile")
    private String mobile;

}
