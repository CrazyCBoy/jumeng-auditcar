package com.jumeng.auditcar.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 订单详情
 */
@Entity
@Data
public class OrderInfo extends BaseEntity{
    public static final String[] ignoreProperty = new String[] {"id", "createDate", "modifyDate", "createBy", "updateBy"};
    @Column(columnDefinition="INT(11) COMMENT '用户编号'")
    private String uid;
    @Column(columnDefinition="INT(11) COMMENT '检测站编号'")
    private String csid;
    @Column(columnDefinition="VARCHAR(100) COMMENT '预约电话'")
    private String order_phone;
    @Column(columnDefinition="VARCHAR(200) COMMENT '订单创建时间'")
    private String order_create_time;
    @Column(columnDefinition="VARCHAR(50) COMMENT '订单开始时间'")
    private String orderstart_time;
    @Column(columnDefinition="VARCHAR(50) COMMENT '订单取消时间'")
    private String  orderoff_time ;
    @Column(columnDefinition="VARCHAR(50) COMMENT '订单状态'")
    private int  order_status ;
    @Column(columnDefinition="VARCHAR(50) COMMENT '订单完成时间'")
    private String  orderfinish_time ;
    @Column(columnDefinition="VARCHAR(50) COMMENT '实际支付金额'")
    private String  pay_money;
    @Column(columnDefinition="VARCHAR(50) COMMENT '支付时间'")
    private String  pay_time ;
    @Column(columnDefinition="VARCHAR(50) COMMENT '付款退回时间'")
    private String  payback_time;
    @Column(columnDefinition="VARCHAR(50) COMMENT '支付方式'")
    private String  pay_way ;
    @Column(columnDefinition="VARCHAR(50) COMMENT '支付状态'")
    private String  pay_status;
    @Column(columnDefinition="VARCHAR(50) COMMENT '订单途径'")
    private String  order_way ;
}
