package com.jumeng.auditcar.domain.dao;


import com.jumeng.auditcar.domain.MerchantRole;
import com.jumeng.auditcar.domain.reposiotry.BaseRepository;

public interface MerchantRoleDao extends BaseRepository<MerchantRole, String> {

    MerchantRole findByName(String name);
}
