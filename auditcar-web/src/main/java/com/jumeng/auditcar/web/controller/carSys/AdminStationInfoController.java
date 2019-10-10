package com.jumeng.auditcar.web.controller.carSys;

import com.jumeng.auditcar.domain.StationInfo;
import com.jumeng.auditcar.domain.exception.AdminErrorType;
import com.jumeng.auditcar.domain.form.param.StationInfoParam;
import com.jumeng.auditcar.domain.service.MerchantService;
import com.jumeng.auditcar.domain.service.StationInfoService;
import com.jumeng.auditcar.domain.vo.Result;
import com.jumeng.auditcar.web.controller.admin.AdminBaseController;
import com.jumeng.auditcar.web.form.StationInfoForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @ClassName AdminShopInfoController
 * @Description TODO
 * @Author 46330
 * @Date 2019/9/11 10:59
 * @Version 1.0
 **/
@Slf4j
@RestController
@RequestMapping("/admin/shopInfo")
@Api(tags = {"审车点操作API"})
public class AdminStationInfoController extends AdminBaseController {

    @Autowired
    private StationInfoService stationInfoService;
    @Autowired
    private MerchantService merchantService;


    @ApiOperation(value="新增审车点", notes="传入审车点,进行数据创建")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stationInfoForm", value = "实体", required = true, dataType = "StationInfoForm", paramType = "body")
    })
    @ResponseBody
    @RequestMapping(value = "saveShopInfo", method = RequestMethod.POST)
    public Result saveShopInfo(@Valid @RequestBody StationInfoForm stationInfoForm){
        log.info("stationInfoForm:{}", stationInfoForm);
        StationInfo oldStationInfo = stationInfoService.findStationInfoByShopId(stationInfoForm.getStation_id());
        if(oldStationInfo != null){
            return Result.fail(AdminErrorType.INVOID_DATA_EXIST);
        }
        StationInfo stationInfo = stationInfoForm.toPo(StationInfo.class, StationInfo.ignoreProperty);

        stationInfo = stationInfoService.saveStationInfo(stationInfo);
        return Result.success(stationInfo.getId());
    }

    @ApiOperation(value="修改审车点信息", notes="传入审车点实体,进行数据修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stationInfoForm", value = "实体", required = true, dataType = "StationInfoForm", paramType = "body")
    })
    @ResponseBody
    @RequestMapping(value = "updateStationInfo", method = RequestMethod.POST)
    public Result updateStationInfo(@Valid @RequestBody StationInfoForm stationInfoForm){
        log.info("stationInfoForm:{}", stationInfoForm);
        StationInfo stationInfo = stationInfoService.findStationInfoById(stationInfoForm.getId());
        stationInfo.setStationname(stationInfoForm.getStationname());
        stationInfo.setAddress(stationInfoForm.getAddress());
        stationInfo = stationInfoService.updateStationInfo(stationInfo);
        return Result.success(stationInfo);
    }

    
    @ApiOperation(value="获取审车点列表(分页)", notes="根据传入的查询条件,查询审车点列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stationInfoParam", value = "查询条件", required = false, dataType = "StationInfoParam", paramType = "body")
    })
    @ResponseBody
    @RequestMapping(value = "findStationInfo", method = RequestMethod.POST)
    public Result findStationInfo(@Valid @RequestBody StationInfoParam stationInfoParam){
        log.info("stationInfoParam):{}", stationInfoParam);
        Page<StationInfo> page = stationInfoService.findStationInfoPage(stationInfoParam);
        return Result.success(page);
    }
}
