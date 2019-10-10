package com.jumeng.auditcar.domain.dao;

import com.jumeng.auditcar.domain.OrderInfo;
import com.jumeng.auditcar.domain.reposiotry.BaseRepository;

public interface OrderInfoDao  extends BaseRepository<OrderInfo,String> {
    OrderInfo findByUid(String uid);

    OrderInfo findByIdAndUid(String id,String uid);
}
