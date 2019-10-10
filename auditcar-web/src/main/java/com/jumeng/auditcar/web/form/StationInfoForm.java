package com.jumeng.auditcar.web.form;

import com.jumeng.auditcar.domain.StationInfo;
import com.jumeng.auditcar.domain.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName StationInfoForm
 * @Description TODO
 * @Author 46330
 * @Date 2019/9/12 9:55
 * @Version 1.0
 **/
@ApiModel(description = "审车点实体")
@Data
public class StationInfoForm extends BaseForm<StationInfo> {

    @NotNull(message = "审车点名称不能为空")
    @ApiModelProperty(value = "审车点名称", example="审车点AAAA", required=true)
    private String stationname;

    @NotNull(message = "审车点地址不能为空")
    @ApiModelProperty(value = "审车点地址", example="XX路XX号", required=true)
    private String address;

    @NotNull(message = "审车点编码不能为空")
    @ApiModelProperty(value = "审车点编码", example="13", required=true)
    private String station_id;


}
