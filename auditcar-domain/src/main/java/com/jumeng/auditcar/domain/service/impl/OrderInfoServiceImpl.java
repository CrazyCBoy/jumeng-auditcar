package com.jumeng.auditcar.domain.service.impl;

import com.jumeng.auditcar.domain.OrderInfo;
import com.jumeng.auditcar.domain.dao.OrderInfoDao;
import com.jumeng.auditcar.domain.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private OrderInfoDao orderInfoDao;

    @Override
    public OrderInfo findByUid(String uid) {
        return orderInfoDao.findByUid(uid);
    }

    @Override
    public OrderInfo saveOrderInfo(OrderInfo orderInfo) {

        return orderInfoDao.save(orderInfo);
    }

    @Override
    public OrderInfo findByIdAndUid(String id, String uid) {
        return orderInfoDao.findByIdAndUid(id,uid);
    }
}
