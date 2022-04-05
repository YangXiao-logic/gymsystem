package com.gym.controller;


import com.gym.common.api.CommonResult;
import com.gym.mbg.model.Facility;
import com.gym.mbg.model.SingleActivity;
import com.gym.service.FacilityService;
import com.gym.service.SingleActivityService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "FacilityController", description = "设备管理")
@RestController
@RequestMapping("facility")
public class FacilityController {

    @Autowired
    private SingleActivityService singleActivityService;

    @Autowired
    private FacilityService facilityService;

    @GetMapping("{id}")
    public CommonResult<List<SingleActivity>> getFacilityNextWeekActivity(@PathVariable Integer id){
        Facility facility = facilityService.getFacility(id);
        List<SingleActivity> activityList = singleActivityService.selectByFacilityByWeek(facility);
        return CommonResult.success(activityList);
    }



}
