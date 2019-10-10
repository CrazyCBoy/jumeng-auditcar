package com.jumeng.auditcar.web.controller.admin;

import com.jumeng.auditcar.web.security.JWTUserDetails;
import com.jumeng.auditcar.web.security.JWTService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName AdminAuthController
 * @Description TODO
 * @Author 46330
 * @Date 2019/9/17 18:01
 * @Version 1.0
 **/
@Slf4j
public class AdminBaseController {

    @Value("${jwt.token}")
    private String tokenHeader;
    @Value("${jwt.startStr}")
    private String tokenStartStr;
    @Autowired
    private JWTService jwtService;

    public JWTUserDetails getLoginAdmin(HttpServletRequest request) {
        final String authHeader = request.getHeader(tokenHeader);
        if (StringUtils.isNotBlank(authHeader) && authHeader.startsWith(tokenStartStr)) {
            String authToke = authHeader.substring(tokenStartStr.length());
            return jwtService.getUserFromToken(authToke);
        }
        return null;
    }
}
