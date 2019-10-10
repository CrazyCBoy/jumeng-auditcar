package com.jumeng.auditcar.web.service;


import com.jumeng.auditcar.web.security.JWTUserDetails;

public interface AuthService {

    JWTUserDetails merchantLogin(String username, String password);

    JWTUserDetails adminLogin(String username, String password);
}
