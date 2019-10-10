package com.jumeng.auditcar.domain.service.impl;

import com.jumeng.auditcar.domain.AdminRole;
import com.jumeng.auditcar.domain.dao.AdminRoleDao;
import com.jumeng.auditcar.domain.form.param.AdminRoleParam;
import com.jumeng.auditcar.domain.service.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * @ClassName AdminRoleServiceImpl
 * @Description TODO
 * @Author 46330
 * @Date 2019/9/11 10:56
 * @Version 1.0
 **/
@Service
@CacheConfig(cacheNames = "adminRoleCache")
public class AdminRoleServiceImpl implements AdminRoleService {

    @Autowired
    private AdminRoleDao adminRoleDao;

    @Override
    @CachePut(key = "#result.id", unless = "#result eq null")
    public AdminRole saveAdminRole(AdminRole adminRole) {
        return adminRoleDao.save(adminRole);
    }

    @Override
    @CachePut(key = "#result.id", unless = "#result eq null")
    public AdminRole updateAdminRole(AdminRole adminRole) {
        return adminRoleDao.save(adminRole);
    }

    @Override
    @Cacheable(key = "#adminRoleId", unless = "#result eq null")
    public AdminRole findAdminRoleById(String adminRoleId) {
        return adminRoleDao.findById(adminRoleId).orElse(null);
    }

    @Override
    public AdminRole findAdminRoleByName(String username) {
        return adminRoleDao.findByName(username);
    }

    @Override
    @CacheEvict(key = "#adminRoleId")
    public void deleteAdminRole(String adminRoleId) {
        adminRoleDao.findById(adminRoleId).ifPresent(adminRoleDao::delete);
    }

    @Override
    public Page<AdminRole> findAdminRolePage(AdminRoleParam adminRoleParam) {
        return adminRoleDao.findAll(adminRoleParam);
    }
}
