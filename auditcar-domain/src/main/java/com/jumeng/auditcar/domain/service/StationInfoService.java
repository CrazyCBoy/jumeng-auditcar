package com.jumeng.auditcar.domain.service;

import com.jumeng.auditcar.domain.StationInfo;
import com.jumeng.auditcar.domain.form.param.StationInfoParam;
import com.jumeng.auditcar.domain.form.param.nopage.StationInfoNoPageParam;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StationInfoService {
    StationInfo findStationInfoById(String shopInfoId);

    StationInfo findStationInfoByShopId(String StationId);

    void deleteStationInfo(StationInfo stationInfo);

    StationInfo saveStationInfo(StationInfo stationInfo);

    StationInfo updateStationInfo(StationInfo stationInfo);

    Page<StationInfo> findStationInfoPage(StationInfoParam StationInfoParam);

    List<StationInfo> findStationInfoList(StationInfoNoPageParam StationInfoNoPageParam);
}
