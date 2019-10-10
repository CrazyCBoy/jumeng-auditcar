package com.jumeng.auditcar.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 车辆信息
 */
@Entity
@Data
public class CarInfo extends BaseEntity{
    public static final String[] ignoreProperty = new String[] {"id", "createDate", "modifyDate", "createBy", "updateBy"};
    @Column(columnDefinition="INT(11) COMMENT '用户编号'")
    private String uid;
    @Column(columnDefinition="VARCHAR(50) COMMENT '车牌号'")
    private String carnum;
    @Column(columnDefinition="VARCHAR(100) COMMENT '行驶证'")
    private String pictureinfo;
    @Column(columnDefinition="VARCHAR(200) COMMENT '车牌类型'")
    private String carvariety;
    @Column(unique = true, columnDefinition="VARCHAR(50) COMMENT '车主姓名'")
    private String carname;
    @Column(columnDefinition="VARCHAR(200) COMMENT '车主电话'")
    private String carphone;
    @Column(columnDefinition="VARCHAR(50) COMMENT '车架号'")
    private String carvin;
    @Column(columnDefinition="VARCHAR(50) COMMENT '发动机号'")
    private String enginenum;
    @Column(columnDefinition="VARCHAR(50) COMMENT '发动机号'")
    private String plateColor;
    @Column(columnDefinition="VARCHAR(50) COMMENT '号牌种类描述'")
    private String note;
}
