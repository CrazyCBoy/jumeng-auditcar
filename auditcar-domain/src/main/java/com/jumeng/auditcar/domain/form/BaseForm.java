package com.jumeng.auditcar.domain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

@Slf4j
@Data
public class BaseForm<T> {
    @ApiModelProperty(value = "主键ID(新增时不传, 修改必传)", example="10fd27b0-8c84-4032-b964-80910f84dff0")
    private String id;
    /**
     * From转化为Po，进行后续业务处理
     *
     * @param clazz
     * @return
     */
    public T toPo(Class<T> clazz, String[] ignoreProperty) {
        T t = BeanUtils.instantiateClass(clazz);
        BeanUtils.copyProperties(this, t, ignoreProperty);
        return t;
    }
}
