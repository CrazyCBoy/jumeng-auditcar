package com.jumeng.auditcar.domain.service.impl;

import com.jumeng.auditcar.domain.StationInfo;
import com.jumeng.auditcar.domain.dao.StationInfoDao;
import com.jumeng.auditcar.domain.form.param.StationInfoParam;
import com.jumeng.auditcar.domain.form.param.nopage.StationInfoNoPageParam;
import com.jumeng.auditcar.domain.service.StationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ShopInfoServiceImpl
 * @Description TODO
 * @Author 46330
 * @Date 2019/9/11 10:56
 * @Version 1.0
 **/
@Service
@CacheConfig(cacheNames = "stationInfo")
public class StationInfoServiceImpl implements StationInfoService {

    @Autowired
    private StationInfoDao stationInfoDao;

    // unless = "#result eq null" 返回结果为null的不缓存
    @Override
    @CachePut(key = "#result.stationId", unless = "#result eq null")
    public StationInfo saveStationInfo(StationInfo stationInfo) {
        return stationInfoDao.save(stationInfo);
    }

    @Override
    @CachePut(key = "#result.stationId", unless = "#result eq null")
    public StationInfo updateStationInfo(StationInfo stationInfo) {
        return stationInfoDao.save(stationInfo);
    }

    @Override
    public StationInfo findStationInfoById(String stationInfoId) {
        return stationInfoDao.findById(stationInfoId).orElse(null);
    }

    @Override
    @Cacheable(key = "#stationId", unless = "#result eq null")
    public StationInfo findStationInfoByShopId(String stationpId) {
        return stationInfoDao.findByStationId(stationpId);
    }

    @Override
    @CacheEvict(key = "#stationInfo.stationId")
    public void deleteStationInfo(StationInfo stationInfo) {
        stationInfoDao.findById(stationInfo.getId()).ifPresent(stationInfoDao::delete);
    }

    @Override
    public Page<StationInfo> findStationInfoPage(StationInfoParam stationInfoParam) {
        return stationInfoDao.findAll(stationInfoParam);
    }

    @Override
    public List<StationInfo> findStationInfoList(StationInfoNoPageParam StationInfoNoPageParam) {
        return stationInfoDao.findAll(StationInfoNoPageParam);
    }
}
