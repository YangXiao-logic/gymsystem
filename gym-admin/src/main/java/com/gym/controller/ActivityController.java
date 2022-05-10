package com.gym.controller;


import com.gym.common.api.CommonResult;
import com.gym.service.SingleOrderService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "ActivityController", description = "活动管理")
@RestController
@RequestMapping("activity")
public class ActivityController {

    @Autowired
    private SingleOrderService singleOrderService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityController.class);

//    @GetMapping
//    public CommonResult addNextWeek(){
//        CommonResult commonResult;
//        int count = singleOrderService.createSingleActivityForAllFacilityForWeek();
//        if (count == 1) {
//            commonResult = CommonResult.success("操作成功");
//        } else {
//            commonResult = CommonResult.failed("操作失败");
//
//        }
//        return commonResult;
//    }





}
