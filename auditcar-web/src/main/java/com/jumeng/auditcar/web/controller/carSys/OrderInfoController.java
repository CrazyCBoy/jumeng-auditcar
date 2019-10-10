package com.jumeng.auditcar.web.controller.carSys;


import com.jumeng.auditcar.domain.OrderInfo;
import com.jumeng.auditcar.domain.form.param.OrderInfoParam;
import com.jumeng.auditcar.domain.service.OrderInfoService;
import com.jumeng.auditcar.domain.vo.Result;
import com.jumeng.auditcar.web.form.OrderInfoForm;
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
@RequestMapping("/order")
@Api(tags = {"订单操作API"})
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;

    @ApiOperation(value="用户新增订单", notes="传入用户的订单信息,进行订单信息数据的创建")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderInfoForm", value = "订单信息实体", required = true, dataType = "OrderInfoForm", paramType = "body")
    })
    @ResponseBody
    @RequestMapping(value = "saveOrderInfo", method = RequestMethod.POST)
    public Result saveOrderInfo(@Valid @RequestBody OrderInfoForm orderInfoForm){
        log.info("carInfoForm:{}", orderInfoForm);
        OrderInfo orderInfo= orderInfoForm.toPo(OrderInfo.class,OrderInfo.ignoreProperty);
        orderInfo.setUid(orderInfoForm.getUid());
        orderInfo=orderInfoService.saveOrderInfo(orderInfo);
        return Result.success(orderInfo);
    };

    @ApiOperation(value="用户订单详情", notes="获取用户订单详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderInfoForm", value = "订单详情", required = true, dataType = "OrderInfoForm", paramType = "body")
    })
    @ResponseBody
    @RequestMapping(value = "findOrderInfoByIdAndUid", method = RequestMethod.POST)
    public Result findOrderInfoByIdAndUid(@Valid @RequestBody OrderInfoForm orderInfoForm){
        log.info("OrderInfoForm:{}", orderInfoForm);
        OrderInfo orderInfo=orderInfoService.findByIdAndUid(orderInfoForm.getId(),orderInfoForm.getUid());
        return Result.success(orderInfo);
    };
    @ApiOperation(value="修改订单信息", notes="修改订单,进行订单信息的修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderInfoForm", value = "订单实体", required = true, dataType = "OrderInfoForm", paramType = "body")
    })
    @ResponseBody
    @RequestMapping(value = "updateOrderInfoByIdAndUid", method = RequestMethod.POST)
    public Result updateOrderInfoByIdAndUid(@Valid @RequestBody OrderInfoForm orderInfoForm){
        OrderInfo orderInfo=orderInfoService.findByIdAndUid(orderInfoForm.getId(),orderInfoForm.getUid());
        orderInfo.setOrder_status(1);
        OrderInfo newOrderInfo=orderInfoService.saveOrderInfo(orderInfo);
        return Result.success(newOrderInfo);
    }

    @ApiOperation(value="获取该用户所有订单信息（分页）", notes="根据传入的查询条件,查询订单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderInfoParam", value = "查询条件", required = false, dataType = "OrderInfoParam", paramType = "body")
    })
    @ResponseBody
    @RequestMapping(value = "findPayInfoByUid", method = RequestMethod.POST)
    public Result findPayInfoByUid(@Valid @RequestBody OrderInfoParam orderInfoParam){
        log.info("payInfoParam):{}", orderInfoParam);
        String uid=orderInfoParam.getUid();
        OrderInfo orderInfo =orderInfoService.findByUid(uid);
        return Result.success(orderInfo);
    }
}
