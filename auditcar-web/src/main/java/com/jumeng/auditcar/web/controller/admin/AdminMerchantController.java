package com.jumeng.auditcar.web.controller.admin;

import com.jumeng.auditcar.common.SerialNumberUtil;
import com.jumeng.auditcar.domain.Merchant;
import com.jumeng.auditcar.domain.MerchantRole;
import com.jumeng.auditcar.domain.exception.AdminErrorType;
import com.jumeng.auditcar.domain.exception.SystemErrorType;
import com.jumeng.auditcar.domain.form.param.MerchantParam;
import com.jumeng.auditcar.domain.service.MerchantRoleService;
import com.jumeng.auditcar.domain.service.MerchantService;
import com.jumeng.auditcar.domain.vo.Result;
import com.jumeng.auditcar.web.form.MerchantForm;
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
 * @ClassName MerchantController
 * @Description TODO
 * @Author 46330
 * @Date 2019/9/11 10:59
 * @Version 1.0
 **/
@Slf4j
@RestController
@RequestMapping("/merchant/merchant")
@Api(tags = {"商户操作API"})
public class AdminMerchantController extends AdminBaseController {

    @Autowired
    private MerchantService merchantService;
    @Autowired
    private MerchantRoleService merchantRoleService;

    @ApiOperation(value="新增商户", notes="传入商户实体,进行商户数据创建")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "merchantForm", value = "会员实体", required = true, dataType = "MerchantForm", paramType = "body")
    })
    @ResponseBody
    @RequestMapping(value = "saveMerchant", method = RequestMethod.POST)
    public Result saveMerchant(@Valid @RequestBody MerchantForm merchantForm){
        log.info("merchantForm:{}", merchantForm);
        Merchant oldMerchant = merchantService.findMerchantByMobile(merchantForm.getMobile());
        if(oldMerchant != null){
            return Result.fail(AdminErrorType.INVOID_PARAM_USER_EXIST);
        }
        Merchant merchant = merchantForm.toPo(Merchant.class, Merchant.ignoreProperty);
        List<String> roleNameList = new ArrayList<>();
        List<String> roleIdList = new ArrayList<>();
        for (String roleId : merchantForm.getRoleIdList()) {
            if(StringUtils.isNotEmpty(roleId)) {
                MerchantRole merchantRole = merchantRoleService.findMerchantRoleById(roleId);
                if(merchantRole != null) {
                    roleNameList.add(merchantRole.getName());
                    roleIdList.add(merchantRole.getId());
                }
            }
        }
        merchant.setRoleIdList(roleIdList);
        merchant.setRoleNameList(roleNameList);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        merchant.setPassword(encoder.encode(merchant.getPassword()));
        merchant.setAppId(SerialNumberUtil.buildAppId());
        merchant.setAppSecret(SerialNumberUtil.buildToken(32));
        merchant = merchantService.saveMerchant(merchant);
        return Result.success(merchant.getId());
    }

    @ApiOperation(value="修改商户", notes="传入商户实体,进行商户数据修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "merchantForm", value = "管理员实体", required = true, dataType = "MerchantForm", paramType = "body")
    })
    @ResponseBody
    @RequestMapping(value = "updateMerchant", method = RequestMethod.POST)
    public Result updateMerchant(@Valid @RequestBody MerchantForm merchantForm){
        log.info("merchantForm:{}", merchantForm);
        if(StringUtils.isBlank(merchantForm.getId())){
            return Result.fail(SystemErrorType.ARGUMENT_NOT_VALID);
        }
        Merchant merchant = merchantService.findMerchantById(merchantForm.getId());
        if (merchant == null){
            return Result.fail(AdminErrorType.INVOID_DATA_ID);
        }
        Merchant oldMerchant = merchantService.findMerchantByMobile(merchantForm.getMobile());
        if(oldMerchant != null && !oldMerchant.getId().equals(merchantForm.getId())){
            return Result.fail(AdminErrorType.INVOID_PARAM_USER_EXIST);
        }
        List<String> ignorePropertyList = new ArrayList<>(Arrays.asList(Merchant.ignoreProperty));
        ignorePropertyList.add("password");
        ignorePropertyList.add("roleIdList");
        BeanUtils.copyProperties(merchantForm, merchant, ignorePropertyList.toArray(new String[ignorePropertyList.size()]));
        if(StringUtils.isNotBlank(merchantForm.getPassword())){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            merchant.setPassword(encoder.encode(merchantForm.getPassword()));
        }
        List<String> roleNameList = new ArrayList<>();
        List<String> roleIdList = new ArrayList<>();
        for (String roleId : merchantForm.getRoleIdList()) {
            if(StringUtils.isNotEmpty(roleId)) {
                MerchantRole merchantRole = merchantRoleService.findMerchantRoleById(roleId);
                if(merchantRole != null) {
                    roleNameList.add(merchantRole.getName());
                    roleIdList.add(merchantRole.getId());
                }
            }
        }
        merchant.setRoleIdList(roleIdList);
        merchant.setRoleNameList(roleNameList);
        merchant = merchantService.updateMerchant(merchant);
        return Result.success(merchant.getId());
    }

    @ApiOperation(value="修改商户状态", notes="修改商户可用状态/锁定状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键D", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "状态  true:可用/锁定  false:不可用/解锁 ", required = true, dataType = "Boolean", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "修改类型 enable:是否可用  lock:是否锁定", required = true, dataType = "String", paramType = "query")
    })
    @ResponseBody
    @RequestMapping(value = "updateMerchantStatus", method = RequestMethod.POST)
    public Result updateMerchantStatus(String id, Boolean status, String type){
        if(StringUtils.isBlank(id) || StringUtils.isBlank(type) || (!"enable".equals(type) && !"lock".equals(type)) || status == null){
            return Result.fail(SystemErrorType.ARGUMENT_NOT_VALID);
        }
        Merchant merchant = merchantService.findMerchantById(id);
        if (merchant == null){
            return Result.fail(AdminErrorType.INVOID_DATA_ID);
        }
        if("enable".equals(type)){
            merchant.setIsAccountEnabled(status);
        }else if("lock".equals(type)){
            merchant.setIsAccountLocked(status);
        }else{
            return Result.fail(SystemErrorType.ARGUMENT_NOT_VALID);
        }
        merchant = merchantService.updateMerchant(merchant);
        return Result.success(merchant.getId());
    }
    
    @ApiOperation(value="获取商户列表(分页)", notes="根据传入的查询条件,查询商户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "merchantParam", value = "查询条件", required = false, dataType = "MerchantParam", paramType = "body")
    })
    @ResponseBody
    @RequestMapping(value = "listMerchantByPage", method = RequestMethod.POST)
    public Result listMerchantByPage(@Valid @RequestBody MerchantParam merchantParam){
        log.info("merchantParam):{}", merchantParam);
        Page<Merchant> page =  merchantService.findMerchantPage(merchantParam);
        return Result.success(page);
    }
}
