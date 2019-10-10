package com.jumeng.auditcar.domain.service;

import com.jumeng.auditcar.domain.AdminRole;
import com.jumeng.auditcar.domain.form.param.AdminRoleParam;
import org.springframework.data.domain.Page;

public interface AdminRoleService {
    AdminRole findAdminRoleById(String adminRoleId);

    AdminRole findAdminRoleByName(String name);

    void deleteAdminRole(String adminRoleId);

    AdminRole saveAdminRole(AdminRole adminRole);

    AdminRole updateAdminRole(AdminRole adminRole);

    Page<AdminRole> findAdminRolePage(AdminRoleParam adminRoleParam);
}
