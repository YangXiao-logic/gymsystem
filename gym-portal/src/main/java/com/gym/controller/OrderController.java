package com.gym.controller;


import com.gym.common.api.CommonResult;
import com.gym.mbg.model.SingleOrder;
import com.gym.service.SingleOrderService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private SingleOrderService singleOrderService;


    @ApiOperation("预定活动")
    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public CommonResult<SingleOrder> book(@RequestBody SingleOrder singleOrder){
        CommonResult commonResult;
        int count = singleOrderService.order(singleOrder);
        if (count == 1) {
            commonResult = CommonResult.success(singleOrder);
            LOGGER.debug("order success:{}", singleOrder);
        } else if(count == 2){
            commonResult = CommonResult.failed("余额不足");
            LOGGER.debug("order failed:{}", singleOrder);
        }
        else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("order failed:{}", singleOrder);
        }
        return commonResult;
    }


    @ApiOperation("取消活动")
    @RequestMapping(value = "/cancel",method = RequestMethod.DELETE)
    public CommonResult cancelOrder(@RequestParam int facility_id,
                                    @RequestParam String dateStr
    ) throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(dateStr);
        int count = singleOrderService.cancel(facility_id,date);
        if (count == 1) {
            LOGGER.debug("cancel success");
            return CommonResult.success(null);
        } else {
            LOGGER.debug("cancel failed");
            return CommonResult.failed("操作失败");
        }
    }

    @ApiOperation("用户查看自己的所有预定过的活动")
    @RequestMapping(value = "/check",method = RequestMethod.GET)
    public CommonResult<List<SingleOrder>> checkOrder(){
        return CommonResult.success(singleOrderService.currentCustomerTotalOrder());
    }



}
