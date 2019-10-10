package com.jumeng.auditcar.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jumeng.auditcar.common.DateStrUtil;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author Zhang Meng
 * @Description //实体类-基类
 * @Date 2019/9/11
 **/
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {
	
	public static final String CREATE_DATE_PROPERTY_NAME = "createDate";// "创建日期"属性名称
	public static final String MODIFY_DATE_PROPERTY_NAME = "modifyDate";// "修改日期"属性名称
	public static final String ON_SAVE_METHOD_NAME = "onSave";// "保存"方法名称
	public static final String ON_UPDATE_METHOD_NAME = "onUpdate";// "更新"方法名称

	@Id
	@Column(length = 36, nullable = true, columnDefinition="VARCHAR(36) COMMENT '主键'")
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	protected String id;// ID

	@CreatedBy
	@Column(columnDefinition="VARCHAR(100) COMMENT '创建者'")
	private String createBy;

	@CreatedDate
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(columnDefinition="DATETIME COMMENT '添加时间'")
	protected Date createDate;// 创建日期

	@LastModifiedBy
	@Column(columnDefinition="VARCHAR(100) COMMENT '更新者'")
	private String updateBy;

	@LastModifiedDate
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(columnDefinition="DATETIME COMMENT '修改时间'")
	protected Date modifyDate;// 修改日期

	@Transient
	@JsonIgnore
	public String getCreateDateString()
	{
		if(createDate!=null)
			return DateStrUtil.date2str4sql(createDate);
		else return "";
	}

	@Transient
	@JsonIgnore
	public String getCreateTimeString()
	{
		if(createDate!=null)
			return DateStrUtil.date2str(createDate);
		else return "";
	}
}