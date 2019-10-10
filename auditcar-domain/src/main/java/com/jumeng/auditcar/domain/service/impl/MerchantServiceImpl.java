package com.jumeng.auditcar.domain.service.impl;

import com.jumeng.auditcar.domain.Merchant;
import com.jumeng.auditcar.domain.dao.MerchantDao;
import com.jumeng.auditcar.domain.form.param.MerchantParam;
import com.jumeng.auditcar.domain.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * @ClassName MerchantServiceImpl
 * @Description TODO
 * @Author 46330
 * @Date 2019/9/11 10:56
 * @Version 1.0
 **/
@Service
@CacheConfig(cacheNames = "merchant")
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantDao merchantDao;

    @Override
    @CachePut(key = "#result.mobile", unless = "#result eq null")
    public Merchant saveMerchant(Merchant merchant) {
        return merchantDao.save(merchant);
    }

    @Override
    @CachePut(key = "#result.mobile", unless = "#result eq null")
    public Merchant updateMerchant(Merchant merchant) {
        return merchantDao.save(merchant);
    }

    @Override
    public Merchant findMerchantById(String merchantId) {
        return merchantDao.findById(merchantId).orElse(null);
    }

    @Override
    @Cacheable(key = "#mobile", unless = "#result eq null")
    public Merchant findMerchantByMobile(String mobile) {
        return merchantDao.findByMobile(mobile);
    }

    @Override
    @CacheEvict(key = "#merchant.mobile")
    public void deleteMerchant(Merchant merchant) {
        merchantDao.findById(merchant.getId()).ifPresent(merchantDao::delete);
    }

    @Override
    public Page<Merchant> findMerchantPage(MerchantParam merchantParam) {
        return merchantDao.findAll(merchantParam);
    }
}
