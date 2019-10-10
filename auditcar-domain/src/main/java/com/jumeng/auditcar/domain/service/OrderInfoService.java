package com.jumeng.auditcar.domain.service;

import com.jumeng.auditcar.domain.OrderInfo;

public interface OrderInfoService {
    OrderInfo findByUid(String uid);

    //新增订单
    OrderInfo saveOrderInfo(OrderInfo orderInfo);

    OrderInfo findByIdAndUid(String id,String uid);
}
