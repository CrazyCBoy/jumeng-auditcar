package com.jumeng.auditcar.domain.reposiotry.support;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName DataQueryObjectPage
 * @Description 分页查询对象
 * @Author 46330
 * @Date 2019/9/16 14:38
 * @Version 1.0
 **/
@Data
public class DataQueryObjectPage extends DataQueryObjectSort {

    @ApiModelProperty(value = "页码", example="1", required = true)
    public Integer page = 1;
    @ApiModelProperty(value = "每页大小", example="20", required = true)
    public Integer size = 20;

    public Integer getPage() {
        return page > 0 ? page - 1 : page;
    }
}