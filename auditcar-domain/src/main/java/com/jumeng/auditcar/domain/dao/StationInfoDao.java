package com.jumeng.auditcar.domain.dao;


import com.jumeng.auditcar.domain.StationInfo;
import com.jumeng.auditcar.domain.reposiotry.BaseRepository;

public interface StationInfoDao extends BaseRepository<StationInfo, String> {

    StationInfo findByStationId(String StationId);
}
