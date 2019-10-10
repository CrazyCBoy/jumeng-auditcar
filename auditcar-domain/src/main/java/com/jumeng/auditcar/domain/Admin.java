package com.jumeng.auditcar.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.type.TypeReference;
import com.jumeng.auditcar.common.JsonUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Admin extends BaseEntity{

	public static final String[] ignoreProperty = new String[] {"id", "createDate", "modifyDate", "createBy", "updateBy", "loginFailureCount", "lockedDate", "isSystem", "loginDate", "loginIp"};

	@Column(unique = true, columnDefinition="VARCHAR(50) COMMENT '用户名'")
	private String username;

	@Column(columnDefinition="VARCHAR(100) COMMENT '密码'")
	private String password;

	@Column(columnDefinition="VARCHAR(50) COMMENT '姓名'")
	private String name;

	@Column(columnDefinition="VARCHAR(100) COMMENT '部门'")
	private String department;

	@Column(columnDefinition="BIT default 1 COMMENT '账号是否启用'")
	private Boolean isAccountEnabled;

	@Column(columnDefinition="BIT default 0 COMMENT '账号是否锁定'")
	private Boolean isAccountLocked;

	@Column(columnDefinition="INT(1) default 0 COMMENT '连续登录失败的次数'")
	private Integer loginFailureCount = 0;

	@Column(columnDefinition="DATETIME COMMENT '账号锁定日期'")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lockedDate;

	@Column(columnDefinition="BIT default 0 COMMENT '账号是否系统内置'")
	private Boolean isSystem;

	@Column(columnDefinition="DATETIME COMMENT '最后登录日期'")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date loginDate;

	@Column(columnDefinition="VARCHAR(20) COMMENT '最后登录IP'")
	private String loginIp;

	@Column(columnDefinition="LONGTEXT COMMENT '账号角色ID集合'")
	private String roleIdStore;

	@Column(columnDefinition="LONGTEXT COMMENT '账号角色名称集合'")
	private String roleNameStore;
	@Transient
	public List<String> getRoleIdList() {
		List<String> resultList = new ArrayList<>();
		if(StringUtils.isNotEmpty(roleIdStore)) {
			try {
				resultList = JsonUtil.toObject(roleIdStore, new TypeReference<List<String>>() {});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resultList;
	}

	public void setRoleIdList(List<String> roleIdList) {
		if(roleIdList != null) {
			roleIdStore = JsonUtil.toJson(roleIdList, "yyyy-MM-dd HH:mm:ss");
		}else {
			roleIdStore = null;
		}
	}

	@Transient
	public List<String> getRoleNameList() {
		List<String> resultList = new ArrayList<>();
		if(StringUtils.isNotEmpty(roleNameStore)) {
			try {
				resultList = JsonUtil.toObject(roleNameStore, new TypeReference<List<String>>() {});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resultList;
	}

	public void setRoleNameList(List<String> roleNameList) {
		if(roleNameList != null) {
			roleNameStore = JsonUtil.toJson(roleNameList, "yyyy-MM-dd HH:mm:ss");
		}else {
			roleNameStore = null;
		}
	}
}
