package com.jumeng.auditcar.domain.form.param.nopage;

import com.jumeng.auditcar.domain.reposiotry.support.DataQueryObjectSort;
import com.jumeng.auditcar.domain.reposiotry.support.QueryField;
import com.jumeng.auditcar.domain.reposiotry.support.QueryType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName MerchantRoleParam
 * @Description TODO
 * @Author 46330
 * @Date 2019/9/16 11:54
 * @Version 1.0
 **/
@ApiModel(description = "微信支付配置查询条件")
@Data
public class PayConfigWechatNoPageParam extends DataQueryObjectSort {
    public PayConfigWechatNoPageParam() {
    }

    public PayConfigWechatNoPageParam(String shopInfoId, String merchantId) {
        this.shopInfoId = shopInfoId;
        this.merchantId = merchantId;
    }

    @ApiModelProperty(value = "门店ID")
    @QueryField(type = QueryType.EQUAL, name = "shopInfoId")
    private String shopInfoId;

    @ApiModelProperty(value = "商户ID")
    @QueryField(type = QueryType.EQUAL, name = "merchantId")
    private String merchantId;

}
