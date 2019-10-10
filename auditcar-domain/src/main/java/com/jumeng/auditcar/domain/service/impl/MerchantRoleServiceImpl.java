package com.jumeng.auditcar.domain.service.impl;

import com.jumeng.auditcar.domain.MerchantRole;
import com.jumeng.auditcar.domain.dao.MerchantRoleDao;
import com.jumeng.auditcar.domain.form.param.MerchantRoleParam;
import com.jumeng.auditcar.domain.form.param.nopage.MerchantRoleNoPageParam;
import com.jumeng.auditcar.domain.service.MerchantRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName MerchantRoleServiceImpl
 * @Description TODO
 * @Author 46330
 * @Date 2019/9/11 10:56
 * @Version 1.0
 **/
@Service
@CacheConfig(cacheNames = "merchantRoleCache")
public class MerchantRoleServiceImpl implements MerchantRoleService {

    @Autowired
    private MerchantRoleDao merchantRoleDao;

    @Override
    @CachePut(key = "#result.id", unless = "#result eq null")
    public MerchantRole saveMerchantRole(MerchantRole merchantRole) {
        return merchantRoleDao.save(merchantRole);
    }

    @Override
    @CachePut(key = "#result.id", unless = "#result eq null")
    public MerchantRole updateMerchantRole(MerchantRole merchantRole) {
        return merchantRoleDao.save(merchantRole);
    }

    @Override
    @Cacheable(key = "#merchantRoleId", unless = "#result eq null")
    public MerchantRole findMerchantRoleById(String merchantRoleId) {
        return merchantRoleDao.findById(merchantRoleId).orElse(null);
    }

    @Override
    public MerchantRole findMerchantRoleByName(String username) {
        return merchantRoleDao.findByName(username);
    }

    @Override
    @CacheEvict(key = "#merchantRoleId")
    public void deleteMerchantRole(String merchantRoleId) {
        merchantRoleDao.findById(merchantRoleId).ifPresent(merchantRoleDao::delete);
    }

    @Override
    public Page<MerchantRole> findMerchantRolePage(MerchantRoleParam merchantRoleParam) {
        return merchantRoleDao.findAll(merchantRoleParam);
    }

    @Override
    public List<MerchantRole> findMerchantRoleList(MerchantRoleNoPageParam merchantRoleNoPageParam) {
        return merchantRoleDao.findAll(merchantRoleNoPageParam);
    }
}
