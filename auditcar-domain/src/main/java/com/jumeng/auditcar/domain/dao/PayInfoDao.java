package com.jumeng.auditcar.domain.dao;

import com.jumeng.auditcar.domain.PayInfo;
import com.jumeng.auditcar.domain.reposiotry.BaseRepository;

public interface PayInfoDao extends BaseRepository<PayInfo,String> {
    PayInfo findByUidAndCsid(String udi,String csid);
}
