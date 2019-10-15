package com.jumeng.auditcar.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
public class FunctionMenu extends BaseEntity{
	public static final String[] ignoreProperty = new String[] {"id", "createDate", "modifyDate", "createBy", "updateBy", "fullPath", "fullName", "level"};
	public static final String PATH_SEPARATOR = ",";// 树路径分隔符
	public static final String NAME_SEPARATOR = "/";// 名称路径分隔符

	@Column(columnDefinition="VARCHAR(36) COMMENT '父ID(一级类目ID为NULL)'")
	private String pid;
	@Column(columnDefinition="VARCHAR(10) COMMENT '类型 menu:菜单  function:功能'")
	private String ftype;
	@Column(columnDefinition="VARCHAR(10) COMMENT '归属 merchant:商户  admin:管理员  member:用户'")
	private String fto;
	@Column(columnDefinition="VARCHAR(50) COMMENT '名称'")
	private String name;
	@Column(columnDefinition="VARCHAR(50) COMMENT '权限控制'")
	private String role;
	@Column(columnDefinition="VARCHAR(300) COMMENT '类目层级映射关系'")
	private String fullPath;
	@Column(columnDefinition="VARCHAR(300) COMMENT '完整名称'")
	private String fullName;
	@Column(columnDefinition="INT(2) COMMENT '层级'")
	private Integer level;

}
