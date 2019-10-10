package com.jumeng.auditcar.domain.form.param;

import com.jumeng.auditcar.domain.reposiotry.support.QueryField;
import com.jumeng.auditcar.domain.reposiotry.support.QueryType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(description = "订单信息查询")
@Data
public class OrderInfoParam {

    @ApiModelProperty(value = "用户编号", example = "13")
    @QueryField(type = QueryType.EQUAL, name = "uid")
    private String uid;
}
