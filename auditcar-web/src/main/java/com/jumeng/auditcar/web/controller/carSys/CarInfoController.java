package com.jumeng.auditcar.web.controller.carSys;


import com.jumeng.auditcar.domain.CarInfo;
import com.jumeng.auditcar.domain.form.param.CarInfoParam;
import com.jumeng.auditcar.domain.service.CarInfoService;
import com.jumeng.auditcar.domain.vo.Result;
import com.jumeng.auditcar.web.form.CarInfoForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/car")
@Api(tags = {"车辆信息API"})
public class CarInfoController {
    @Autowired
    private CarInfoService carInfoService;

    @ApiOperation(value="新增车辆信息", notes="传入用户提交的车辆信息,进行车辆信息数据的创建")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "carInfoForm", value = "车辆信息实体", required = true, dataType = "CarInfoForm", paramType = "body")
    })
    @ResponseBody
    @RequestMapping(value = "saveCarInfo", method = RequestMethod.POST)
    public Result saveCarInfo(@Valid @RequestBody CarInfoForm carInfoForm){
        log.info("carInfoForm:{}", carInfoForm);
        CarInfo carInfo= carInfoForm.toPo(CarInfo.class,CarInfo.ignoreProperty);
        carInfo=carInfoService.saveCarInfo(carInfo);
      return Result.success(carInfo);
    };

    @ApiOperation(value="获取该用户审车信息详情（分页）", notes="根据传入的查询条件,查询车辆信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "carInfoParam", value = "查询条件", required = false, dataType = "CarInfoParam", paramType = "body")
    })
    @ResponseBody
    @RequestMapping(value = "findCarInfoByPage", method = RequestMethod.POST)
    public Result findCarInfoByPage(@Valid @RequestBody CarInfoParam carInfoParam){
        log.info("carInfoParam):{}", carInfoParam);
        Page<CarInfo> page =  carInfoService.findCarInfoByPage(carInfoParam);
        return Result.success(page);
    }

}
