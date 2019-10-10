package com.jumeng.auditcar.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jumeng.auditcar.common.JsonUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class AdminRole extends BaseEntity {
	public static final String[] ignoreProperty = new String[] {"id", "createDate", "modifyDate", "createBy", "updateBy", "isSystem"};

	public static final String ROLE_BASE = "ROLE_BASE";// 基础管理权限

	@Column(columnDefinition="VARCHAR(50) COMMENT '角色名称'")
	private String name;
	@Column(columnDefinition="BIT default 0 COMMENT '是否为系统内置'")
	private Boolean isSystem;
	@Column(columnDefinition="VARCHAR(200) COMMENT '描述'")
	private String description;
	@JsonIgnore
	@Column(columnDefinition="LONGTEXT COMMENT '权限集合存储'")
	private String authorityListStore;// 权限集合ID存储

	// 获取权限集合
	@SuppressWarnings("unchecked")
	@Transient
	public List<String> getAuthorityList() {
		if (StringUtils.isEmpty(authorityListStore)) {
			return null;
		}
		return JsonUtil.toObject(authorityListStore, ArrayList.class);
	}
		
	// 设置权限集合
	@Transient
	public void setAuthorityList(List<String> roleList) {
		if (roleList == null || roleList.size() == 0) {
			authorityListStore = null;
			return;
		}
		authorityListStore = JsonUtil.toJson(roleList);
	}
}