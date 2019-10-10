package com.jumeng.auditcar.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class UserInfo extends BaseEntity{

	public static final String[] ignoreProperty = new String[] {"id", "createDate", "modifyDate", "createBy", "updateBy", "payAmount", "lastPayDate", "refundAmount"};

    @Column(columnDefinition="VARCHAR(11) COMMENT '手机号'")
	private String mobile;// 手机号
    @Column(columnDefinition="VARCHAR(50) COMMENT '姓名'")
	private String name;//姓名
	@Column(columnDefinition="INT(11) COMMENT '检测站编号'")
	private String pid;
    @Column(columnDefinition="VARCHAR(20) COMMENT '身份证号'")
	private String cardNum;//身份证号
    @Column(columnDefinition="DATETIME COMMENT '生日'")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date birthday;//生日
    @Column(columnDefinition="VARCHAR(200) COMMENT '地址'")
	private String address;//地址
    @Column(columnDefinition="VARCHAR(50) COMMENT '邮箱'")
	private String email;//邮箱
    @Column(columnDefinition="VARCHAR(10) COMMENT '省'")
	private String province;//省
    @Column(columnDefinition="VARCHAR(10) COMMENT '市'")
	private String city;//市
    @Column(columnDefinition="VARCHAR(10) COMMENT '区/县'")
	private String county;//县级市
    @Column(columnDefinition="VARCHAR(2) COMMENT '性别 F：女性；M：男性'")
	private String memberSex;//性别 F：女性；M：男性
    @Column(columnDefinition="INT(3) COMMENT '年龄'")
	private Integer age;//年龄
    @Column(columnDefinition="VARCHAR(100) COMMENT '头像地址'")
	private String avatar;//头像
    @Column(columnDefinition="VARCHAR(50) COMMENT '昵称'")
	private String nickName;//昵称
    @Column(columnDefinition="VARCHAR(50) COMMENT '招行用户ID'")
	private String cmbUserId;//招商银行用户ID
    @Column(columnDefinition="VARCHAR(50) COMMENT '微信openId'")
	private String wxOpenId;//微信openId
    @Column(columnDefinition="VARCHAR(50) COMMENT '支付宝userId'")
    private String aliUserId;//微信openId
    @Column(columnDefinition="DECIMAL(15,2) default 0 COMMENT '支付金额'")
	private BigDecimal payAmount;//消费总额
    @Column(columnDefinition="DATETIME COMMENT '最后交易时间'")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastPayDate;//最后消费时间
    @Column(columnDefinition="DECIMAL(15,2) default 0 COMMENT '退款金额'")
	private BigDecimal refundAmount;//退款总额
    @Column(columnDefinition="BIT default 1 COMMENT '账号是否启用'")
	private Boolean isAccountEnabled;// 账号是否启用
	@Column(columnDefinition="BIT default 0 COMMENT '账号是否锁定'")
	private Boolean isAccountLocked;// 账号是否锁定
    @Column(columnDefinition="VARCHAR(100) COMMENT '密码'")
	private String password;// 密码

	@Transient
    public String getDesensitizationMemberName() {
    	String memberName = "";
    	if(StringUtils.isNotEmpty(name)) {
    		name = name.trim();
			if(name.length() > 3) {
				memberName = "*" + name.substring(2, name.length());
			}else if(name.length() == 1) {
				memberName = name;
			}else {
				memberName = "*" + name.substring(1, name.length());
			}
		}else {
			memberName = "未知";
		}
    	return memberName;
    }
	
	@Transient
    public String getDesensitizationMemberMobile() {
    	String memberMobile = mobile;
    	if(StringUtils.isNotEmpty(mobile)) {
    		mobile = mobile.trim();
			if(mobile.length() == 11) {
				memberMobile = mobile.substring(0, 3) + "****" + mobile.substring(7, mobile.length());
			}
		}else {
			memberMobile = "";
		}
    	return memberMobile;
    }
	
	@Transient
    public String getMemberName() {
    	String memberName = "";
    	if(StringUtils.isNotEmpty(name)) {
    		memberName = name;
		}else if(StringUtils.isNotEmpty(mobile)) {
    		memberName = mobile;
		}else if(StringUtils.isNotEmpty(nickName)) {
    		memberName = nickName;
		}else {
			memberName = "无名";
		}
    	return memberName;
    }
	
}
