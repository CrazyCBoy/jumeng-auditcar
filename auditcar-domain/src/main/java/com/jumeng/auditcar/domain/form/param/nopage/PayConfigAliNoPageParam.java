package com.jumeng.auditcar.domain.form.param.nopage;

import com.jumeng.auditcar.domain.reposiotry.support.DataQueryObjectSort;
import com.jumeng.auditcar.domain.reposiotry.support.QueryField;
import com.jumeng.auditcar.domain.reposiotry.support.QueryType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName MerchantRoleParam
 * @Description TODO
 * @Author 46330
 * @Date 2019/9/16 11:54
 * @Version 1.0
 **/
@ApiModel(description = "支付宝配置查询条件")
@Data
public class PayConfigAliNoPageParam extends DataQueryObjectSort {

    public PayConfigAliNoPageParam() {
    }

    public PayConfigAliNoPageParam(String appId, String partnerId, String shopInfoId, String merchantId) {
        this.appId = appId;
        this.partnerId = partnerId;
        this.shopInfoId = shopInfoId;
        this.merchantId = merchantId;
    }

    @ApiModelProperty(value = "支付宝APPID", example = "2016110802633310")
    @QueryField(type = QueryType.EQUAL, name = "appId")
    private String appId;

    @ApiModelProperty(value = "支付宝PID", example = "2088602158488105")
    @QueryField(type = QueryType.EQUAL, name = "partnerId")
    private String partnerId;

    @ApiModelProperty(value = "门店ID")
    @QueryField(type = QueryType.EQUAL, name = "shopInfoId")
    private String shopInfoId;

    @NotBlank(message = "商户ID不能为空")
    @ApiModelProperty(value = "商户ID")
    @QueryField(type = QueryType.EQUAL, name = "merchantId")
    private String merchantId;

}
