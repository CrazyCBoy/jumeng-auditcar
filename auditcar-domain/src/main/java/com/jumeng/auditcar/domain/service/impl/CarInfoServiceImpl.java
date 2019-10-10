package com.jumeng.auditcar.domain.service.impl;

import com.jumeng.auditcar.domain.CarInfo;
import com.jumeng.auditcar.domain.dao.CarInfoDao;
import com.jumeng.auditcar.domain.form.param.CarInfoParam;
import com.jumeng.auditcar.domain.service.CarInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class CarInfoServiceImpl implements CarInfoService {
    @Autowired
    private CarInfoDao carInfoDao;

    @Override
    public CarInfo findByUid(String uid) {
        return carInfoDao.findByUid(uid);
    }

    @Override
    public CarInfo saveCarInfo(CarInfo carInfo) {
        return carInfoDao.save(carInfo);
    }

    @Override
    public Page<CarInfo> findCarInfoByPage(CarInfoParam carInfoParam) {
        return carInfoDao.findAll(carInfoParam);
    }
}
