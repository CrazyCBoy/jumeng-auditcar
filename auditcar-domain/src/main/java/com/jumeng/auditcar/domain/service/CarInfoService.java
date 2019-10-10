package com.jumeng.auditcar.domain.service;

import com.jumeng.auditcar.domain.CarInfo;
import com.jumeng.auditcar.domain.form.param.CarInfoParam;
import org.springframework.data.domain.Page;

public interface CarInfoService {
    CarInfo findByUid(String uid);
    CarInfo saveCarInfo(CarInfo carInfo);

    Page<CarInfo> findCarInfoByPage(CarInfoParam carInfoParam);
}
