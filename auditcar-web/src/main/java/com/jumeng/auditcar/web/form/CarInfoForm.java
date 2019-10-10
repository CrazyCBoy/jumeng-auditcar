package com.jumeng.auditcar.web.form;


import com.jumeng.auditcar.domain.CarInfo;
import com.jumeng.auditcar.domain.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel(description = "车辆信息实体")
@Data
public class CarInfoForm  extends BaseForm<CarInfo> {

    @NotBlank(message = "车主姓名不能为空")
    @ApiModelProperty(value = "姓名", example="车辆信息A", required=true)
    private String name;//姓名

    @NotBlank(message = "车主电话不能为空")
    @ApiModelProperty(value = "车主电话", example="1888888888", required=true)
    private String mobile;

    @NotBlank(message = "车牌号不能为空")
    @ApiModelProperty(value = "车牌号", example="豫A888888", required=true)
    private String carnum;

    @NotBlank(message = "车牌类型不能为空")
    @ApiModelProperty(value = "车牌类型", example="大货车小轿车计程车出租车", required=true)
    private String carvariety;

    @NotBlank(message = "用户行车证不能为空")
    @ApiModelProperty(value = "行车证", example="//car/pic/A.jpg", required=true)
    private String pictureinfo;

}
