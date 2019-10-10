package com.jumeng.auditcar.domain.dao;


import com.jumeng.auditcar.domain.Merchant;
import com.jumeng.auditcar.domain.reposiotry.BaseRepository;

public interface MerchantDao extends BaseRepository<Merchant, String> {

    Merchant findByMobile(String mobile);
}
