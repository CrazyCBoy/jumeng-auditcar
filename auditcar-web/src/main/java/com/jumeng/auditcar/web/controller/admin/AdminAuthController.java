package com.jumeng.auditcar.web.controller.admin;

import com.jumeng.auditcar.domain.Admin;
import com.jumeng.auditcar.domain.exception.AuthErrorType;
import com.jumeng.auditcar.domain.service.AdminService;
import com.jumeng.auditcar.domain.vo.Result;
import com.jumeng.auditcar.web.security.JWTUserDetails;
import com.jumeng.auditcar.web.service.AuthService;
import com.jumeng.auditcar.web.param.LoginParam;
import com.jumeng.auditcar.web.security.JWTService;
import com.jumeng.auditcar.web.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

/**
 * @ClassName AdminAuthController
 * @Description TODO
 * @Author 46330
 * @Date 2019/9/17 18:01
 * @Version 1.0
 **/
@Slf4j
@RestController
@RequestMapping("/admin/auth")
@Api(tags = {"登录API"})
public class AdminAuthController extends AdminBaseController{

    @Value("${setting.isLoginFailureLock}")
    private Boolean isLoginFailureLock;
    @Value("${setting.loginFailureLockTime}")
    private Integer loginFailureLockTime;
    @Value("${jwt.expiration}")
    private String expiration;
    @Autowired
    private AuthService authService;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation(value="管理员登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginParam", value = "登录信息", required = false, dataType = "LoginParam", paramType = "body")
    })
    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Result login(@Valid @RequestBody LoginParam loginParam, HttpServletRequest request) {
        try {
            final JWTUserDetails userDetails = authService.adminLogin(loginParam.getUsername(), loginParam.getPassword());
            final String token = jwtService.generateAccessToken(userDetails);
            Map<String, Object> resultMap = new HashMap<>();
            List<String> roleList = new ArrayList<>();
            roleList.addAll(userDetails.getUserRoleList());
            resultMap.put("name", userDetails.getUsername());
            resultMap.put("role", roleList);
            resultMap.put("avatar", userDetails.getAvatar());
            //登录成功,将用户token存入redis
            resultMap.put("token", token);
            String authRedisKey = jwtService.getAuthRedisKey(userDetails.getUserId());
            redisUtil.set(authRedisKey, token, Long.parseLong(expiration));
            Admin admin = adminService.findAdminByUsername(loginParam.getUsername());
            admin.setLoginDate(new Date());
            admin.setLoginIp(request.getRemoteAddr());
            adminService.updateAdmin(admin);
            return Result.success(resultMap);
        } catch (AuthenticationException authenticationException) {
            authenticationException.printStackTrace();
            log.error("authenticationException{}", authenticationException.getStackTrace());
            log.info(authenticationException.getLocalizedMessage());
            if (authenticationException instanceof BadCredentialsException) {
                log.info("loginFailure==您的用户名或密码错误!");
                try {
                    Admin admin = adminService.findAdminByUsername(loginParam.getUsername());
                    if (admin != null) {
                        if(admin.getIsAccountLocked()){
                            return Result.fail(AuthErrorType.FORBIDDEN_LOGIN_FAIL, "您的账号已锁定,请联系管理员处理!");
                        }
                        if(!admin.getIsAccountEnabled()){
                            return Result.fail(AuthErrorType.FORBIDDEN_LOGIN_FAIL, "您的账号已禁用,请联系管理员处理!");
                        }
                        if (isLoginFailureLock) {
                            int loginFailureCount = admin.getLoginFailureCount() + 1;
                            if(loginFailureCount >= loginFailureLockTime){
                                admin.setIsAccountLocked(true);
                                admin.setLockedDate(new Date());
                            }
                            admin.setLoginFailureCount(loginFailureCount);
                            adminService.updateAdmin(admin);
                            if(loginFailureLockTime - loginFailureCount <= 3){
                                log.info("loginFailure==若连续" + loginFailureLockTime + "次密码输入错误,您的账号将被锁定!");
                                return Result.fail(AuthErrorType.FORBIDDEN_LOGIN_FAIL, "若连续" + loginFailureLockTime + "次密码输入错误,您的账号将被锁定!");
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return Result.fail(AuthErrorType.FORBIDDEN_LOGIN_FAIL, "您的用户名或密码错误!");
            } else if (authenticationException instanceof DisabledException) {
                log.info("loginFailure==您的账号已被禁用,无法登录!");
                return Result.fail(AuthErrorType.FORBIDDEN_LOGIN_FAIL, "您的账号已被禁用,无法登录!");
            } else if (authenticationException instanceof LockedException) {
                log.info("loginFailure==您的账号已被锁定,无法登录!");
                return Result.fail(AuthErrorType.FORBIDDEN_LOGIN_FAIL, "您的账号已被锁定,无法登录!");
            } else if (authenticationException instanceof AccountExpiredException) {
                log.info("loginFailure==您的账号已过期,无法登录!", "您的账号已过期,无法登录!");
                return Result.fail(AuthErrorType.FORBIDDEN_LOGIN_FAIL);
            } else if (authenticationException instanceof UsernameNotFoundException) {
                log.info("loginFailure==用户不存在!");
                return Result.fail(AuthErrorType.FORBIDDEN_LOGIN_FAIL, "用户不存在!");
            } else {
                log.info("loginFailure==出现未知错误,无法登录!");
                return Result.fail(AuthErrorType.FORBIDDEN_LOGIN_FAIL, "出现未知错误!");
            }
        }
    }

    @ApiOperation(value="获取管理员信息")
    @ResponseBody
    @RequestMapping(value = "info", method = RequestMethod.POST)
    public Result userInfo(HttpServletRequest request) {
        JWTUserDetails userDetails = getLoginAdmin(request);
        if(userDetails != null){
            Map<String, Object> resultMap = new HashMap<>();
            List<String> roleList = new ArrayList<>();
            roleList.addAll(userDetails.getUserRoleList());
            resultMap.put("name", userDetails.getUsername());
            resultMap.put("role", roleList);
            resultMap.put("avatar", userDetails.getAvatar());
            return Result.success(resultMap);
        }
        return Result.fail(AuthErrorType.FORBIDDEN_NOT_LOGIN);
    }

    @ApiOperation(value="管理员退出")
    @ResponseBody
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public Result logout(HttpServletRequest request) {
        JWTUserDetails userDetails = getLoginAdmin(request);
        if(userDetails != null){
            String authRedisKey = jwtService.getAuthRedisKey(userDetails.getUserId());
            redisUtil.del(authRedisKey);
        }
        return Result.success();
    }
}
