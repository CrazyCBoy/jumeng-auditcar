package com.jumeng.auditcar.web.controller.admin;

import com.jumeng.auditcar.domain.FunctionMenu;
import com.jumeng.auditcar.domain.exception.AdminErrorType;
import com.jumeng.auditcar.domain.exception.SystemErrorType;
import com.jumeng.auditcar.domain.form.param.FunctionMenuParam;
import com.jumeng.auditcar.domain.service.FunctionMenuService;
import com.jumeng.auditcar.domain.vo.Result;
import com.jumeng.auditcar.web.form.FunctionMenuForm;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @ClassName FunctionMenuController
 * @Description TODO
 * @Author 46330
 * @Date 2019/9/11 10:59
 * @Version 1.0
 **/
@Slf4j
@RestController
@RequestMapping("/admin/fncm")
@Api(tags = {"功能菜单API"})
public class AdminFunctionMenuController extends AdminBaseController {

    @Autowired
    private FunctionMenuService functionMenuService;

    @ApiOperation(value="新增功能菜单", notes="传入功能菜单实体,进行数据创建")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "functionMenuForm", value = "实体", required = true, dataType = "FunctionMenuForm", paramType = "body")
    })
    @ResponseBody
    @RequestMapping(value = "saveFunctionMenu", method = RequestMethod.POST)
    public Result saveFunctionMenu(@Valid @RequestBody FunctionMenuForm functionMenuForm){
        log.info("functionMenuForm:{}", functionMenuForm);
        FunctionMenu functionMenu = functionMenuForm.toPo(FunctionMenu.class, FunctionMenu.ignoreProperty);
        functionMenu = functionMenuService.saveFunctionMenu(functionMenu);
        return Result.success(functionMenu.getId());
    }

    @ApiOperation(value="修改功能菜单", notes="传入功能菜单实体,进行数据修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "functionMenuForm", value = "实体", required = true, dataType = "FunctionMenuForm", paramType = "body")
})
    @ResponseBody
    @RequestMapping(value = "updateFunctionMenu", method = RequestMethod.POST)
    public Result updateFunctionMenu(@Valid @RequestBody FunctionMenuForm functionMenuForm){
        log.info("functionMenuForm:{}", functionMenuForm);
        if(StringUtils.isBlank(functionMenuForm.getId())){
            return Result.fail(SystemErrorType.ARGUMENT_NOT_VALID);
        }
        FunctionMenu functionMenu = functionMenuService.findFunctionMenuById(functionMenuForm.getId());
        if (functionMenu == null){
            return Result.fail(AdminErrorType.INVOID_DATA_ID);
        }
        List<String> ignorePropertyList = new ArrayList<>(Arrays.asList(FunctionMenu.ignoreProperty));
        ignorePropertyList.add("pid");
        BeanUtils.copyProperties(functionMenuForm, functionMenu, ignorePropertyList.toArray(new String[ignorePropertyList.size()]));
        functionMenu = functionMenuService.updateFunctionMenu(functionMenu);
        return Result.success(functionMenu.getId());
    }

    @ApiOperation(value="获取菜单列表(树状)", notes="根据传入的查询条件,查询菜单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "父级ID", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "ftype", value = "类型 menu:菜单  function:功能", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "fto", value = "归属 merchant:商户  admin:管理员  member:用户", required = false, dataType = "String", paramType = "query")
    })
    @ResponseBody
    @RequestMapping(value = "listFunctionMenuByTree", method = RequestMethod.POST)
    public Result listFunctionMenuByTree(String pid, String ftype, String fto){
        List<Map<String, Object>> resultList = functionMenuService.findFunctionMenuTreeByPidAndFtypeAndFto(pid, ftype, fto);
        return Result.success(resultList);
    }

    @ApiOperation(value="获取菜单列表(分页)", notes="根据传入的查询条件,查询菜单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "functionMenuParam", value = "查询条件", required = false, dataType = "FunctionMenuParam", paramType = "body")
    })
    @ResponseBody
    @RequestMapping(value = "listFunctionMenuByPage", method = RequestMethod.POST)
    public Result listFunctionMenuByPage(@Valid @RequestBody FunctionMenuParam functionMenuParam){
        log.info("functionMenuParam):{}", functionMenuParam);
        Page<FunctionMenu> page =  functionMenuService.findFunctionMenuPage(functionMenuParam);
        return Result.success(page);
    }
}
