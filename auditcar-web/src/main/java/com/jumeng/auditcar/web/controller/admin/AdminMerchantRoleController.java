package com.jumeng.auditcar.web.controller.admin;

import com.jumeng.auditcar.domain.MerchantRole;
import com.jumeng.auditcar.domain.exception.AdminErrorType;
import com.jumeng.auditcar.domain.exception.SystemErrorType;
import com.jumeng.auditcar.domain.form.param.MerchantRoleParam;
import com.jumeng.auditcar.domain.form.param.nopage.MerchantRoleNoPageParam;
import com.jumeng.auditcar.domain.service.MerchantRoleService;
import com.jumeng.auditcar.domain.vo.Result;
import com.jumeng.auditcar.web.form.MerchantRoleForm;
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
import java.util.List;

/**
 * @ClassName MerchantRoleController
 * @Description TODO
 * @Author 46330
 * @Date 2019/9/11 10:59
 * @Version 1.0
 **/
@Slf4j
@RestController
@RequestMapping("/admin/merchant/role")
@Api(tags = {"商户角色操作API"})
public class AdminMerchantRoleController extends AdminBaseController {

    @Autowired
    private MerchantRoleService merchantRoleService;

    @ApiOperation(value="新增角色", notes="传入实体,进行数据创建")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "merchantRoleForm", value = "商户角色实体", required = true, dataType = "MerchantRoleForm", paramType = "body")
    })
    @ResponseBody
    @RequestMapping(value = "saveMerchantRole", method = RequestMethod.POST)
    public Result saveMerchantRole(@Valid @RequestBody MerchantRoleForm merchantRoleForm){
        log.info("merchantRoleForm:{}", merchantRoleForm);
        MerchantRole merchantRole = merchantRoleForm.toPo(MerchantRole.class, MerchantRole.ignoreProperty);
        merchantRole.setIsSystem(true);
        merchantRole = merchantRoleService.saveMerchantRole(merchantRole);
        return Result.success(merchantRole.getId());
    }

    @ApiOperation(value="修改角色", notes="传入实体,进行数据修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "merchantRoleForm", value = "商户角色实体", required = true, dataType = "MerchantRoleForm", paramType = "body")
    })
    @ResponseBody
    @RequestMapping(value = "updateMerchantRole", method = RequestMethod.POST)
    public Result updateMerchantRole(@Valid @RequestBody MerchantRoleForm merchantRoleForm){
        log.info("merchantRoleForm:{}", merchantRoleForm);
        if(StringUtils.isBlank(merchantRoleForm.getId())){
            return Result.fail(SystemErrorType.ARGUMENT_NOT_VALID);
        }
        MerchantRole merchantRole = merchantRoleService.findMerchantRoleById(merchantRoleForm.getId());
        if (merchantRole == null){
            return Result.fail(AdminErrorType.INVOID_DATA_ID);
        }
        BeanUtils.copyProperties(merchantRoleForm, merchantRole, MerchantRole.ignoreProperty);
        merchantRole = merchantRoleService.updateMerchantRole(merchantRole);
        return Result.success(merchantRole.getId());
    }

    @ApiOperation(value="获取角色列表(分页)", notes="根据传入的查询条件,查询角色列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "merchantRoleParam", value = "查询条件", required = false, dataType = "MerchantRoleParam", paramType = "body")
    })
    @ResponseBody
    @RequestMapping(value = "listMerchantRoleByPage", method = RequestMethod.POST)
    public Result listMerchantRoleByPage(@Valid @RequestBody MerchantRoleParam merchantRoleParam){
        log.info("merchantRoleParam):{}", merchantRoleParam);
        Page<MerchantRole> page =  merchantRoleService.findMerchantRolePage(merchantRoleParam);
        return Result.success(page);
    }

    @ApiOperation(value="获取角色列表", notes="根据传入的查询条件,查询角色列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "merchantRoleNoPageParam", value = "查询条件", required = false, dataType = "MerchantRoleNoPageParam", paramType = "body")
    })
    @ResponseBody
    @RequestMapping(value = "listMerchantRole", method = RequestMethod.POST)
    public Result listMerchantRole(@Valid @RequestBody MerchantRoleNoPageParam merchantRoleNoPageParam){
        log.info("merchantRoleNoPageParam):{}", merchantRoleNoPageParam);
        List<MerchantRole> list =  merchantRoleService.findMerchantRoleList(merchantRoleNoPageParam);
        return Result.success(list);
    }
}
