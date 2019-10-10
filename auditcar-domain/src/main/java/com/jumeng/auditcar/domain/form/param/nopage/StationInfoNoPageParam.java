package com.jumeng.auditcar.domain.form.param.nopage;

import com.jumeng.auditcar.domain.reposiotry.support.DataQueryObjectSort;
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
public class StationInfoNoPageParam extends DataQueryObjectSort {

    @ApiModelProperty(value = "门店名称", example = "测试店")
    @QueryField(type = QueryType.FULL_LIKE, name = "stationname")
    private String stationname;
    @ApiModelProperty(value = "门店地址", example = "XX路")
    @QueryField(type = QueryType.FULL_LIKE, name = "address")
    private String address;
    @ApiModelProperty(value = "门店编码", example = "21014")
    @QueryField(type = QueryType.EQUAL, name = "station_id")
    private String station_id;

}
