package com.jumeng.auditcar.web.controller.carSys;

import com.jumeng.auditcar.domain.UserInfo;
import com.jumeng.auditcar.domain.service.UserService;
import com.jumeng.auditcar.domain.vo.Result;
import com.jumeng.auditcar.web.controller.admin.AdminBaseController;
import com.jumeng.auditcar.web.form.UserForm;
import com.jumeng.auditcar.web.form.UserLoginForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName MemberController
 * @Description TODO
 * @Author 46330
 * @Date 2019/9/11 10:59
 * @Version 1.0
 **/
@Slf4j
@RestController
@RequestMapping("/admin/member")
@Api(tags = {"用户操作API"})
public class AdminUserController extends AdminBaseController {

    @Autowired
    private UserService userService;

    @ApiOperation(value="用户登录", notes="用户登录操作")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userLoginForm", value = "用户实体", required = true, dataType = "UserLoginForm", paramType = "body")
    })
    @ResponseBody
    @RequestMapping(value = "LoginUser", method = RequestMethod.POST)
    public Result LoginUser(@Valid @RequestBody UserLoginForm userLoginForm){
        UserInfo userInfo = userLoginForm.toPo(UserInfo.class, UserInfo.ignoreProperty);
         userInfo=userService.loginUser(userInfo);
        return Result.success(userInfo);
    }

    @ApiOperation(value="填写个人资料", notes="传入用户的信息,进行用户数据的更新")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userForm", value = "填写资料", required = true, dataType = "UserForm", paramType = "body")
    })
    @ResponseBody
    @RequestMapping(value = "updateUserInfo", method = RequestMethod.POST)
    public Result updateUserInfo(@Valid @RequestBody UserForm userForm){
        UserInfo userInfo=userService.findMemberByMobile(userForm.getMobile());
        List<String> ignorePropertyList = new ArrayList<>(Arrays.asList(UserInfo.ignoreProperty));
        BeanUtils.copyProperties(userForm, userInfo, ignorePropertyList.toArray(new String[ignorePropertyList.size()]));
        userInfo=userService.updateUserInfo(userInfo);
        return Result.success(userInfo);
    }

//    @ApiOperation(value="获取用户列表(分页)", notes="根据传入的查询条件,查询会员列表")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "userParam", value = "查询条件", required = false, dataType = "UserParam", paramType = "body")
//    })
//    @ResponseBody
//    @RequestMapping(value = "listMemberByPage", method = RequestMethod.POST)
//    public Result UserListByPage(@Valid @RequestBody UserParam userParam){
//        log.info("userParam):{}", userParam);
//        Page<UserInfo> page =  userService.findMemberPage(userParam);
//        return Result.success(page);
//    }
}
