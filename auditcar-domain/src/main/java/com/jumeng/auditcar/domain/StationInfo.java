package com.jumeng.auditcar.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
public class StationInfo extends BaseEntity{
	public static final String[] ignoreProperty = new String[] {"id", "createDate", "modifyDate", "createBy", "updateBy"};
	@Column(columnDefinition="VARCHAR(100) COMMENT '审车点名称'")
	private String stationname;
	@Column(columnDefinition="VARCHAR(200) COMMENT '审车点地址'")
	private String address;
	@Column(unique = true, columnDefinition="VARCHAR(50) COMMENT '审车点编码'")
	private String stationId;
	@Column(columnDefinition="VARCHAR(36) COMMENT '审车点负责人'")
	private String linkman;
	@Column(columnDefinition="VARCHAR(100) COMMENT '审车点电话'")
	private String stationphone;
    @Column(columnDefinition="VARCHAR(50) COMMENT '经度'")
    private String  lon ;
    @Column(columnDefinition="VARCHAR(50) COMMENT '纬度'")
    private String  lat ;
    @Column(columnDefinition="VARCHAR(50) COMMENT '所属区域编号'")
    private int  aid ;
    @Column(columnDefinition="VARCHAR(50) COMMENT '图片信息'")
    private String  picinfo ;
    @Column(columnDefinition="VARCHAR(50) COMMENT '检测站状态  T：营业中；F：休息中'")
    private String  cs_status;
    @Column(columnDefinition="VARCHAR(50) COMMENT '是否向第三方转单 Y:转单；N：不转单'")
    private String  disanf ;
    @Column(columnDefinition="VARCHAR(50) COMMENT '营业时间'")
    private String  workday ;
    @Column(columnDefinition="VARCHAR(50) COMMENT '上午营业时间'")
    private String worktime_am ;
    @Column(columnDefinition="VARCHAR(50) COMMENT '下午营业时间'")
    private String  worktime_pm;
}
