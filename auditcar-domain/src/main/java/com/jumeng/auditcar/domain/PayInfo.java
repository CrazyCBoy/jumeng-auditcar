package com.jumeng.auditcar.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;


/**
 * 支付信息
 */
@Entity
@Data
public class PayInfo extends BaseEntity{


    public static final String[] ignoreProperty = new String[] {"id", "createDate", "modifyDate", "createBy", "updateBy", "payAmount", "lastPayDate", "refundAmount"};

    @Column(columnDefinition="VARCHAR(50) COMMENT '订单编号'")
    private String  oid ;
    @Column(columnDefinition="VARCHAR(50) COMMENT '下单时间'")
    private String  orderstart_time;
    @Column(columnDefinition="VARCHAR(50) COMMENT '需支付总额'")
    private int  amount_money ;
    @Column(columnDefinition="VARCHAR(50) COMMENT '实际支付金额'")
    private String pay_money ;
    @Column(columnDefinition="VARCHAR(50) COMMENT '支付时间'")
    private String  pay_time ;
    @Column(columnDefinition="VARCHAR(50) COMMENT '支付方式'")
    private String  pay_way ;
    @Column(columnDefinition="VARCHAR(50) COMMENT '支付状态'")
    private String  pay_status ;
    @Column(columnDefinition="VARCHAR(50) COMMENT '支付宝授权码'")
    private String  auth_token ;
    @Column(columnDefinition="VARCHAR(50) COMMENT '优惠券金额'")
    private String  coupon_money;
    @Column(columnDefinition="VARCHAR(50) COMMENT '交易流水号'")
    private String  serial ;
    @Column(columnDefinition="VARCHAR(50) COMMENT '付款退回时间'")
    private String  payback_time ;
    @Column(columnDefinition="VARCHAR(50) COMMENT '用户编号'")
    private String  uid;
    @Column(columnDefinition="VARCHAR(50) COMMENT '用户名称'")
    private String  username ;
    @Column(columnDefinition="VARCHAR(50) COMMENT '站点名称'")
    private String  csname ;
    @Column(columnDefinition="VARCHAR(50) COMMENT '站点编号'")
    private String  csid;



}
