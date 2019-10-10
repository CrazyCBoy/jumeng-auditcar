package com.jumeng.auditcar.domain.service;

import com.jumeng.auditcar.domain.Merchant;
import com.jumeng.auditcar.domain.form.param.MerchantParam;
import org.springframework.data.domain.Page;

public interface MerchantService {
    Merchant findMerchantById(String merchantId);

    Merchant findMerchantByMobile(String mobile);

    void deleteMerchant(Merchant merchant);

    Merchant saveMerchant(Merchant merchant);

    Merchant updateMerchant(Merchant merchant);

    Page<Merchant> findMerchantPage(MerchantParam merchantParam);
}
