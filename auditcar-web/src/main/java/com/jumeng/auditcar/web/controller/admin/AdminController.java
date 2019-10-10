package com.jumeng.auditcar.web.controller.admin;

import com.jumeng.auditcar.domain.Admin;
import com.jumeng.auditcar.domain.AdminRole;
import com.jumeng.auditcar.domain.exception.AdminErrorType;
import com.jumeng.auditcar.domain.exception.SystemErrorType;
import com.jumeng.auditcar.domain.form.param.AdminParam;
import com.jumeng.auditcar.domain.service.AdminRoleService;
import com.jumeng.auditcar.domain.service.AdminService;
import com.jumeng.auditcar.domain.vo.Result;
import com.jumeng.auditcar.web.form.AdminForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName AdminController
 * @Description TODO
 * @Author 46330
 * @Date 2019/9/11 10:59
 * @Version 1.0
 **/
@Slf4j
@RestController
@RequestMapping("/admin/admin")
@Api(tags = {"管理员操作API"})
public class AdminController extends AdminBaseController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminRoleService adminRoleService;

    @ApiOperation(value="新增管理员", notes="传入管理员实体,进行会员数据创建")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminForm", value = "管理员实体", required = true, dataType = "AdminForm", paramType = "body")
    })
    @ResponseBody
    @RequestMapping(value = "saveAdmin", method = RequestMethod.POST)
    public Result saveAdmin(@Valid @RequestBody AdminForm adminForm){
        log.info("adminForm:{}", adminForm);
        Admin oldAdmin = adminService.findAdminByUsername(adminForm.getUsername());
        if(oldAdmin != null){
            return Result.fail(AdminErrorType.INVOID_PARAM_USER_EXIST);
        }
        Admin admin = adminForm.toPo(Admin.class, Admin.ignoreProperty);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        admin.setPassword(encoder.encode(admin.getPassword()));

        List<String> roleNameList = new ArrayList<>();
        List<String> roleIdList = new ArrayList<>();
        for (String roleId : adminForm.getRoleIdList()) {
            if(StringUtils.isNotEmpty(roleId)) {
                AdminRole adminRole = adminRoleService.findAdminRoleById(roleId);
                if(adminRole != null) {
                    roleNameList.add(adminRole.getName());
                    roleIdList.add(adminRole.getId());
                }
            }
        }
        admin.setRoleIdList(roleIdList);
        admin.setRoleNameList(roleNameList);
        admin = adminService.saveAdmin(admin);
        return Result.success(admin.getId());
    }

    @ApiOperation(value="修改管理员", notes="传入管理员实体,进行管理员数据修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminForm", value = "管理员实体", required = true, dataType = "AdminForm", paramType = "body")
    })
    @ResponseBody
    @RequestMapping(value = "updateAdmin", method = RequestMethod.POST)
    public Result updateAdmin(@Valid @RequestBody AdminForm adminForm){
        log.info("adminForm:{}", adminForm);
        if(StringUtils.isBlank(adminForm.getId())){
            return Result.fail(SystemErrorType.ARGUMENT_NOT_VALID);
        }
        Admin admin = adminService.findAdminById(adminForm.getId());
        if (admin == null){
            return Result.fail(AdminErrorType.INVOID_DATA_ID);
        }
        Admin oldAdmin = adminService.findAdminByUsername(adminForm.getUsername());
        if(oldAdmin != null && !oldAdmin.getId().equals(adminForm.getId())){
            return Result.fail(AdminErrorType.INVOID_PARAM_USER_EXIST);
        }
        List<String> ignorePropertyList = new ArrayList<>(Arrays.asList(Admin.ignoreProperty));
        ignorePropertyList.add("password");
        ignorePropertyList.add("roleIdList");
        BeanUtils.copyProperties(adminForm, admin, ignorePropertyList.toArray(new String[ignorePropertyList.size()]));
        if(StringUtils.isNotBlank(adminForm.getPassword())){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            admin.setPassword(encoder.encode(adminForm.getPassword()));
        }
        List<String> roleNameList = new ArrayList<>();
        List<String> roleIdList = new ArrayList<>();
        for (String roleId : adminForm.getRoleIdList()) {
            if(StringUtils.isNotEmpty(roleId)) {
                AdminRole adminRole = adminRoleService.findAdminRoleById(roleId);
                if(adminRole != null) {
                    roleNameList.add(adminRole.getName());
                    roleIdList.add(adminRole.getId());
                }
            }
        }
        admin.setRoleIdList(roleIdList);
        admin.setRoleNameList(roleNameList);
        admin = adminService.updateAdmin(admin);
        return Result.success(admin.getId());
    }

    @ApiOperation(value="修改管理员账号状态", notes="修改管理员账号可用状态/锁定状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键D", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "状态 ", required = true, dataType = "Boolean", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "修改类型 enable:是否可用  1:lock", required = true, dataType = "String", paramType = "query")
    })
    @ResponseBody
    @RequestMapping(value = "updateAdminStatus", method = RequestMethod.POST)
    public Result updateAdminStatus(String id, Boolean status, String type){
        if(StringUtils.isBlank(id) || StringUtils.isBlank(type) || (!"enable".equals(type) && !"lock".equals(type)) || status == null){
            return Result.fail(SystemErrorType.ARGUMENT_NOT_VALID);
        }
        Admin admin = adminService.findAdminById(id);
        if (admin == null){
            return Result.fail(AdminErrorType.INVOID_DATA_ID);
        }
        if("enable".equals(type)){
            admin.setIsAccountEnabled(status);
        }else if("lock".equals(type)){
            admin.setIsAccountLocked(status);
        }else{
            return Result.fail(SystemErrorType.ARGUMENT_NOT_VALID);
        }
        admin = adminService.updateAdmin(admin);
        return Result.success(admin.getId());
    }

    @ApiOperation(value="获取管理员列表(分页)", notes="根据传入的查询条件,查询会员列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminParam", value = "查询条件", required = false, dataType = "AdminParam", paramType = "body")
    })
    @ResponseBody
    @RequestMapping(value = "listAdminByPage", method = RequestMethod.POST)
    public Result listAdminByPage(@Valid @RequestBody AdminParam adminParam){
        log.info("adminParam):{}", adminParam);
        Page<Admin> page =  adminService.findAdminPage(adminParam);
        return Result.success(page);
    }
}
