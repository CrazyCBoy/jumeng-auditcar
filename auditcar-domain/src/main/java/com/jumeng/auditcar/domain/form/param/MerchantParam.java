package com.jumeng.auditcar.domain.form.param;

import com.jumeng.auditcar.domain.reposiotry.support.DataQueryObjectPage;
import com.jumeng.auditcar.domain.reposiotry.support.QueryField;
import com.jumeng.auditcar.domain.reposiotry.support.QueryType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName MerchantParam
 * @Description TODO
 * @Author 46330
 * @Date 2019/9/16 11:54
 * @Version 1.0
 **/
@ApiModel(description = "商户查询条件")
@Data
public class MerchantParam extends DataQueryObjectPage {

    @ApiModelProperty(value = "名称", example = "聚梦科技")
    @QueryField(type = QueryType.FULL_LIKE, name = "name")
    private String name;

    @ApiModelProperty(value = "手机号", example = "13523448667")
    @QueryField(type = QueryType.EQUAL, name = "mobile")
    private String mobile;

    @ApiModelProperty(value = "是否启用", example = "true")
    @QueryField(type = QueryType.EQUAL, name = "isAccountEnabled")
    private Boolean isAccountEnabled;

    @ApiModelProperty(value = "是否锁定", example = "false")
    @QueryField(type = QueryType.EQUAL, name = "isAccountLocked")
    private Boolean isAccountLocked;

    @ApiModelProperty(value = "所属商户", example = "21932d87-51b0-45e5-a4ef-29dd3bb19a83")
    @QueryField(type = QueryType.EQUAL, name = "merchantId")
    private String merchantId;

}
