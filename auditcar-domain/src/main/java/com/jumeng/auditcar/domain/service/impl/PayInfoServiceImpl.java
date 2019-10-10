package com.jumeng.auditcar.domain.service.impl;

import com.jumeng.auditcar.domain.PayInfo;
import com.jumeng.auditcar.domain.dao.PayInfoDao;
import com.jumeng.auditcar.domain.service.PayInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PayInfoServiceImpl implements PayInfoService {
    @Autowired
    private PayInfoDao payInfoDao;
    @Override
    public PayInfo findByUidAndCsid(String uid, String csid) {
        return payInfoDao.findByUidAndCsid(uid,csid);
    }

    @Override
    public PayInfo savePayInfo(PayInfo payInfo) {
       return payInfoDao.save(payInfo);
    }
}
