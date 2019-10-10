package com.jumeng.auditcar.domain.form.param;


import com.jumeng.auditcar.domain.reposiotry.support.DataQueryObjectPage;
import com.jumeng.auditcar.domain.reposiotry.support.QueryField;
import com.jumeng.auditcar.domain.reposiotry.support.QueryType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@ApiModel(description = "支付信息查询")
@Data
public class PayInfoParam extends DataQueryObjectPage {

    @ApiModelProperty(value = "订单编号", example = "MM6317569841")
    @QueryField(type = QueryType.FULL_LIKE, name = "oid")
    private String oid;

    @ApiModelProperty(value = "交易流水号", example = "MS815697531569654")
    @QueryField(type = QueryType.EQUAL, name = "serial")
    private String serial;

    @ApiModelProperty(value = "用户编号", example = "MM6317569841")
    @QueryField(type = QueryType.FULL_LIKE, name = "uid")
    private String uid;

    @ApiModelProperty(value = "站点编号", example = "51ea43a8-85c5-4f7c-a8ca-8d4383b26656")
    @QueryField(type = QueryType.EQUAL, name = "csid")
    private String csid;
}
