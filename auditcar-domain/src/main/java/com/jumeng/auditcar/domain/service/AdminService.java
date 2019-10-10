package com.jumeng.auditcar.domain.service;

import com.jumeng.auditcar.domain.Admin;
import com.jumeng.auditcar.domain.form.param.AdminParam;
import org.springframework.data.domain.Page;

public interface AdminService {
    Admin findAdminById(String adminId);

    Admin findAdminByUsername(String username);

    void deleteAdmin(Admin admin);

    Admin saveAdmin(Admin admin);

    Admin updateAdmin(Admin admin);

    Page<Admin> findAdminPage(AdminParam adminParam);
}
