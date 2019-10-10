package com.jumeng.auditcar.domain.dao;

import com.jumeng.auditcar.domain.AdminRole;
import com.jumeng.auditcar.domain.reposiotry.BaseRepository;

public interface AdminRoleDao extends BaseRepository<AdminRole, String> {

    AdminRole findByName(String name);
}
