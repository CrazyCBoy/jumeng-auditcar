package com.jumeng.auditcar.domain.service.impl;

import com.jumeng.auditcar.domain.Admin;
import com.jumeng.auditcar.domain.dao.AdminDao;
import com.jumeng.auditcar.domain.form.param.AdminParam;
import com.jumeng.auditcar.domain.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * @ClassName AdminServiceImpl
 * @Description TODO
 * @Author 46330
 * @Date 2019/9/11 10:56
 * @Version 1.0
 **/
@Service("adminService")
@CacheConfig(cacheNames = "adminCache")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    @CachePut(key = "#result.username", unless = "#result eq null")
    public Admin saveAdmin(Admin admin) {
        return adminDao.save(admin);
    }

    @Override
    @CachePut(key = "#result.username", unless = "#result eq null")
    public Admin updateAdmin(Admin admin) {
        return adminDao.save(admin);
    }

    @Override
    public Admin findAdminById(String adminId) {
        return adminDao.findById(adminId).orElse(null);
    }

    @Override
    @Cacheable(key = "#username", unless = "#result eq null")
    public Admin findAdminByUsername(String username) {
        return adminDao.findByUsername(username);
    }

    @Override
    @CacheEvict(key = "#admin.username")
    public void deleteAdmin(Admin admin) {
        adminDao.findById(admin.getId()).ifPresent(adminDao::delete);
    }

    @Override
    public Page<Admin> findAdminPage(AdminParam adminParam) {
        return adminDao.findAll(adminParam);
    }
}
