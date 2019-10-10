package com.jumeng.auditcar.domain.reposiotry.support;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName DataQueryObjectSort
 * @Description 排序查询对象
 * @Author 46330
 * @Date 2019/9/16 14:38
 * @Version 1.0
 **/
@Data
public class DataQueryObjectSort implements DataQueryObject {
    @ApiModelProperty(value = "排序字段", example="createDate", required = false)
    public String propertyName;
    @ApiModelProperty(value = "是否升序", example="false", required = false)
    public boolean ascending = true;

}