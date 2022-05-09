package com.gym.controller;


import com.gym.common.api.CommonResult;
import com.gym.mbg.model.Facility;
import com.gym.mbg.model.FacilityTimeTable;
import com.gym.service.FacilityService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/facility")
public class FacilityController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FacilityController.class);
    @Autowired
    private FacilityService facilityService;

    @ApiOperation("按时间查看所有开发设施")
    @RequestMapping(value = "/dayOfAll", method = RequestMethod.GET)
    public CommonResult<List<Facility>> getFacilityListByDay(@RequestParam String dateStr) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(dateStr);
        CommonResult<List<Facility>> success = CommonResult.success(facilityService.getFacilityListByDay(date));
        return success;
    }

    @ApiOperation("查看单个设施所有开发日期")
    @RequestMapping(value = "/showDay/{id}", method = RequestMethod.GET)
    public CommonResult<List<FacilityTimeTable>> getFacilityTimeTable(@PathVariable("id") int id){
        return CommonResult.success(facilityService.getFacilityOpenDay(id));
    }






}
