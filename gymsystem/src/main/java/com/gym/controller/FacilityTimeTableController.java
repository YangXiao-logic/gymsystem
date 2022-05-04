package com.gym.controller;


import com.gym.common.api.CommonResult;
import com.gym.mbg.model.Facility;
import com.gym.mbg.model.FacilityTimeTable;
import com.gym.service.FacilityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("facilityTime")
public class FacilityTimeTableController {
    @Autowired
    private FacilityService facilityService;

    private static final Logger LOGGER = LoggerFactory.getLogger(FacilityController.class);

    @PostMapping
    public CommonResult openFacility(@RequestBody FacilityTimeTable facilityTimeTable){
        CommonResult commonResult;
        int count = facilityService.openFacility(facilityTimeTable);
        if (count == 1) {
            commonResult = CommonResult.success(facilityTimeTable);
            LOGGER.debug("createProduct success:{}", facilityTimeTable);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("createProduct failed:{}", facilityTimeTable);
        }
        return commonResult;
    }

}
