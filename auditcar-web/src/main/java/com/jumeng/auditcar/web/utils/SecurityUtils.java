package com.jumeng.auditcar.web.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @ClassName SecurityUtils
 * @Description TODO
 * @Author 46330
 * @Date 2019/9/18 15:41
 * @Version 1.0
 **/
public class SecurityUtils {
    /**
     * 根据 Authorization 获取当前登录的用户
     *
     * @return 返回用户id
     */
    public static String getCurrentUserName() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String userId = "system";
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                userId = springSecurityUser.getUsername();
            } else if (authentication.getPrincipal() instanceof String) {
                userId = (String) authentication.getPrincipal();
            }
        }
        return userId;
    }

    
}
