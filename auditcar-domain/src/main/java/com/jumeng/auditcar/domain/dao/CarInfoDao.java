package com.jumeng.auditcar.domain.dao;

import com.jumeng.auditcar.domain.CarInfo;
import com.jumeng.auditcar.domain.reposiotry.BaseRepository;

public interface CarInfoDao  extends BaseRepository<CarInfo, String> {
    CarInfo findByUid(String uid);

}
