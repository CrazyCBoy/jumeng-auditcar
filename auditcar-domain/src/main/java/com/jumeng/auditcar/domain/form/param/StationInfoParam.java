package com.jumeng.auditcar.domain.form.param;

import com.jumeng.auditcar.domain.reposiotry.support.DataQueryObjectPage;
import com.jumeng.auditcar.domain.reposiotry.support.QueryField;
import com.jumeng.auditcar.domain.reposiotry.support.QueryType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName StationInfoParam
 * @Description TODO
 * @Author 46330
 * @Date 2019/9/16 11:54
 * @Version 1.0
 **/
@ApiModel(description = "审车点查询条件")
@Data
public class StationInfoParam extends DataQueryObjectPage {

    @ApiModelProperty(value = "审车点名称", example = "审车点AAAAAAA")
    @QueryField(type = QueryType.FULL_LIKE, name = "stationname")
    private String stationname;
    @ApiModelProperty(value = "审车点地址", example = "XX路XXX胡同XXXJJJ")
    @QueryField(type = QueryType.FULL_LIKE, name = "address")
    private String address;
    @ApiModelProperty(value = "审车点编码", example = "13")
    @QueryField(type = QueryType.EQUAL, name = "station_id")
    private String station_id;


}
