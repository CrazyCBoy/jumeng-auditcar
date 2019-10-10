package com.jumeng.auditcar.domain.service;

import com.jumeng.auditcar.domain.UserInfo;
import com.jumeng.auditcar.domain.form.param.UserParam;
import org.springframework.data.domain.Page;

public interface UserService {
    UserInfo findMemberById(String memberId);

    UserInfo findMemberByMobile(String mobile);

    void deleteMember(String memberId);

    //新增用户操作
    UserInfo saveMember(UserInfo userInfo);

    Page<UserInfo> findMemberPage(UserParam userParam);

    //用户登录操作
    UserInfo loginUser(UserInfo userInfo);

    //更新用户信息操作
    UserInfo updateUserInfo(UserInfo userInfo);
}
