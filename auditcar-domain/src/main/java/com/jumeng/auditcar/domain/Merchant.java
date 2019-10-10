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

/**
 * @Author Zhang Meng
 * @Description //实体类-商户
 * @Date 2019/9/11
 **/
@Entity
@Data
public class Merchant extends BaseEntity {
	public static final String[] ignoreProperty = new String[] {"id", "createDate", "modifyDate", "createBy", "updateBy", "loginFailureCount", "lockedDate", "isSystem", "registerIp", "loginDate", "loginIp", "appId", "appSecret"};
	@Column(unique = true, columnDefinition="VARCHAR(11) COMMENT '手机号'")
	private String mobile;// 手机
	@Column(columnDefinition="VARCHAR(100) COMMENT '密码'")
	private String password;// 密码
	@Column(columnDefinition="VARCHAR(50) COMMENT '名称'")
	private String name; // 名称
	@Column(columnDefinition="VARCHAR(200) COMMENT '地址'")
	private String addr; // 地址
	@Column(columnDefinition="VARCHAR(100) COMMENT 'logo'")
	private String logo; // logo
	@Column(columnDefinition="BIT default 1 COMMENT '账号是否启用'")
	private Boolean isAccountEnabled;// 账号是否启用
	@Column(columnDefinition="BIT default 0 COMMENT '账号是否锁定'")
	private Boolean isAccountLocked;// 账号是否锁定
	@Column(columnDefinition="INT(1) default 0 COMMENT '连续登录失败的次数'")
	private Integer loginFailureCount = 0;// 连续登录失败的次数
	@Column(columnDefinition="DATETIME COMMENT '账号锁定日期'")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lockedDate;// 账号锁定日期
	@Column(columnDefinition="VARCHAR(20) COMMENT '注册IP'")
	private String registerIp;// 注册IP
	@Column(columnDefinition="VARCHAR(20) COMMENT '最后登录IP'")
	private String loginIp;// 最后登录IP
	@Column(columnDefinition="DATETIME COMMENT '最后登录日期'")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date loginDate;// 最后登录日期
	@Column(columnDefinition="VARCHAR(36) COMMENT '所属商户'")
	private String merchantId;//所属商户
	@Column(columnDefinition="LONGTEXT COMMENT '账号角色ID集合'")
	private String roleIdStore;//账号权限ID集合
	@Column(columnDefinition="LONGTEXT COMMENT '账号角色名称集合'")
	private String roleNameStore;//账号权限角色名称集合
	@Column(updatable = false, columnDefinition="VARCHAR(100) COMMENT '核销接口appid'")
	private String appId; //核销接口appid
	@Column(updatable = false, columnDefinition="VARCHAR(100) COMMENT '核销接口appsecret'")
	private String appSecret; //核销接口appsecret

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