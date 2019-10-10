package com.jumeng.auditcar.web.controller.admin;

import com.jumeng.auditcar.domain.AdminRole;
import com.jumeng.auditcar.domain.exception.AdminErrorType;
import com.jumeng.auditcar.domain.exception.SystemErrorType;
import com.jumeng.auditcar.domain.form.param.AdminRoleParam;
import com.jumeng.auditcar.domain.service.AdminRoleService;
import com.jumeng.auditcar.domain.vo.Result;
import com.jumeng.auditcar.web.form.AdminRoleForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @ClassName AdminRoleController
 * @Description TODO
 * @Author 46330
 * @Date 2019/9/11 10:59
 * @Version 1.0
 **/
@Slf4j
@RestController
@RequestMapping("/admin/role")
@Api(tags = {"管理员角色操作API"})
public class AdminRoleController extends AdminBaseController {

    @Autowired
    private AdminRoleService adminRoleService;

    @ApiOperation(value="新增角色", notes="传入实体,进行数据创建")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminRoleForm", value = "管理员角色实体", required = true, dataType = "AdminRoleForm", paramType = "body")
    })
    @ResponseBody
    @RequestMapping(value = "saveAdminRole", method = RequestMethod.POST)
    public Result saveAdminRole(@Valid @RequestBody AdminRoleForm adminRoleForm){
        log.info("adminRoleForm:{}", adminRoleForm);
        AdminRole adminRole = adminRoleForm.toPo(AdminRole.class, AdminRole.ignoreProperty);
        adminRole.setIsSystem(false);
        adminRole = adminRoleService.saveAdminRole(adminRole);
        return Result.success(adminRole.getId());
    }

    @ApiOperation(value="修改角色", notes="传入实体,进行数据修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminRoleForm", value = "管理员角色实体", required = true, dataType = "AdminRoleForm", paramType = "body")
    })
    @ResponseBody
    @RequestMapping(value = "updateAdminRole", method = RequestMethod.POST)
    public Result updateAdminRole(@Valid @RequestBody AdminRoleForm adminRoleForm){
        log.info("adminRoleForm:{}", adminRoleForm);
        if(StringUtils.isBlank(adminRoleForm.getId())){
            return Result.fail(SystemErrorType.ARGUMENT_NOT_VALID);
        }
        AdminRole adminRole = adminRoleService.findAdminRoleById(adminRoleForm.getId());
        if (adminRole == null){
            return Result.fail(AdminErrorType.INVOID_DATA_ID);
        }
        BeanUtils.copyProperties(adminRoleForm, adminRole, AdminRole.ignoreProperty);
        adminRole = adminRoleService.updateAdminRole(adminRole);
        return Result.success(adminRole.getId());
    }

    @ApiOperation(value="获取角色列表(分页)", notes="根据传入的查询条件,查询角色列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminRoleParam", value = "查询条件", required = false, dataType = "AdminRoleParam", paramType = "body")
    })
    @ResponseBody
    @RequestMapping(value = "listAdminRoleByPage", method = RequestMethod.POST)
    public Result listAdminRoleByPage(@Valid @RequestBody AdminRoleParam adminRoleParam){
        log.info("adminRoleParam):{}", adminRoleParam);
        Page<AdminRole> page =  adminRoleService.findAdminRolePage(adminRoleParam);
        return Result.success(page);
    }
}
