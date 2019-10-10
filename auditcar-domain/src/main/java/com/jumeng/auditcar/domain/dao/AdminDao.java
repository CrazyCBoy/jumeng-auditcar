package com.jumeng.auditcar.domain.dao;

import com.jumeng.auditcar.domain.Admin;
import com.jumeng.auditcar.domain.reposiotry.BaseRepository;


public interface AdminDao extends BaseRepository<Admin, String> {

    Admin findByUsername(String username);
}
