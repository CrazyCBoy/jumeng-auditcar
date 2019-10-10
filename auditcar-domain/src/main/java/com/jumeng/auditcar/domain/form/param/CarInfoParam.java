package com.jumeng.auditcar.domain.form.param;

import com.jumeng.auditcar.domain.reposiotry.support.DataQueryObjectPage;
import com.jumeng.auditcar.domain.reposiotry.support.QueryField;
import com.jumeng.auditcar.domain.reposiotry.support.QueryType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(description = "车辆信息查询")
@Data
public class CarInfoParam extends DataQueryObjectPage {

    @ApiModelProperty(value = "车主姓名", example = "13")
    @QueryField(type = QueryType.FULL_LIKE, name = "carname")
    private String carname;

    @ApiModelProperty(value = "用户编号", example = "51ea43a8-85c5-4f7c-a8ca-8d4383b26656")
    @QueryField(type = QueryType.EQUAL, name = "uid")
    private String uid;

}
