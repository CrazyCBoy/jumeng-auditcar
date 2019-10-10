package com.jumeng.auditcar.domain.service.impl;

import com.jumeng.auditcar.domain.UserInfo;
import com.jumeng.auditcar.domain.dao.UserDao;
import com.jumeng.auditcar.domain.form.param.UserParam;
import com.jumeng.auditcar.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author 46330
 * @Date 2019/9/11 10:56
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserInfo saveMember(UserInfo userInfo) {
        return userDao.save(userInfo);
    }

    @Override
    public UserInfo findMemberById(String memberId) {
        return userDao.findById(memberId).orElse(null);
    }

    @Override
    public UserInfo findMemberByMobile(String mobile) {
        return userDao.findByMobile(mobile);
    }

    @Override
    public void deleteMember(String memberId) {
        userDao.findById(memberId).ifPresent(userDao::delete);
    }

    @Override
    public Page<UserInfo> findMemberPage(UserParam userParam) {
        return userDao.findAll(userParam);
    }

    @Override
    public UserInfo loginUser(UserInfo userInfo) {
        //1.用户通过第三方登录，通过手机号查询是否为已注册会员
        //2.若是已注册会员，则直接登录
        //3.若是未注册会员，则短信注册并保存用户信息

        UserInfo userdata=findMemberByMobile(userInfo.getMobile());//执行了两次sql查询，一次通过电话  一次通过id
        if(userdata==null){
            //通过该手机号未查询到该会员信息
            //1.进行短信验证登录
            //String phonemsg=QuerySendDetails.getPhonemsg(userInfo.getMobile());
            // {"Message":"OK","RequestId":"FCFC0FC9-A25F-4254-BA55-81A251B36F81","BizId":"621819870689354473^0","Code":"OK"}
            return userDao.save(userInfo);
        }
        //若是会员，则直接通过手机号登录,返回用户信息
        return userDao.findByMobile(userInfo.getMobile());
    }

    @Override
    public UserInfo updateUserInfo(UserInfo newInfo) {
        //saveAndFlush 此方法先根据ID==null 判断是使用 persist方法 还是merge方法 之后
        //会根据数据库中ID是否有记录 来决定 是否要使用insert 还是 update
        return userDao.saveAndFlush(newInfo);
    }
}
