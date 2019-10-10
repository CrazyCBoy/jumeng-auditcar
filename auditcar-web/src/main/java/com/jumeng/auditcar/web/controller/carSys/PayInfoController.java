package com.jumeng.auditcar.web.controller.carSys;

import com.jumeng.auditcar.domain.PayInfo;
import com.jumeng.auditcar.domain.form.param.PayInfoParam;
import com.jumeng.auditcar.domain.service.PayInfoService;
import com.jumeng.auditcar.domain.vo.Result;
import com.jumeng.auditcar.web.form.PayInfoForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Slf4j
@RestController
@RequestMapping("/pay")
@Api(tags = {"支付操作API"})
public class PayInfoController {
    @Autowired
    private PayInfoService payInfoService;

    @ApiOperation(value="支付信息记录", notes="传入用户的支付信息,进行支付信息数据的创建")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "payInfoForm", value = "支付信息实体", required = true, dataType = "PayInfoForm", paramType = "body")
    })
    @ResponseBody
    @RequestMapping(value = "savePayInfo", method = RequestMethod.POST)
    public Result savePayInfo(@Valid @RequestBody PayInfoForm payInfoForm){
        log.info("payInfoForm:{}", payInfoForm);
        PayInfo payInfo= payInfoForm.toPo(PayInfo.class,PayInfo.ignoreProperty);
        payInfo=payInfoService.savePayInfo(payInfo);
        return Result.success(payInfo);
    };

    @ApiOperation(value="获取该用户审车信息详情（分页）", notes="根据传入的查询条件,查询车辆信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "payInfoParam", value = "查询条件", required = false, dataType = "PayInfoParam", paramType = "body")
    })
    @ResponseBody
    @RequestMapping(value = "findPayInfoByUidAndCsid", method = RequestMethod.POST)
    public Result findPayInfoByUidAndCsid(@Valid @RequestBody PayInfoParam payInfoParam){
        log.info("payInfoParam):{}", payInfoParam);
        String uid=payInfoParam.getUid();
        String csid=payInfoParam.getCsid();
        PayInfo payInfo =  payInfoService.findByUidAndCsid(uid,csid);
        return Result.success(payInfo);
    }
}
