package com.jumeng.auditcar.domain.service;

import com.jumeng.auditcar.domain.PayInfo;

public interface PayInfoService {
    PayInfo findByUidAndCsid(String udi,String csid);

    PayInfo savePayInfo(PayInfo payInfo);
}
