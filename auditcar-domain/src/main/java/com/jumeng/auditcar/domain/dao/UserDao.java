package com.jumeng.auditcar.domain.dao;

import com.jumeng.auditcar.domain.UserInfo;
import com.jumeng.auditcar.domain.reposiotry.BaseRepository;


public interface UserDao extends BaseRepository<UserInfo, String> {

    UserInfo findByMobile(String mobile);


}
