package com.gym.service.impl;

import com.gym.GymApplication;
import com.gym.mbg.model.Facility;
import com.gym.service.FacilityService;
import com.gym.service.SingleOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Date;


@SpringBootTest
public class SingleOrderServiceImplTest {

    @Autowired
    SingleOrderService singleOrderService;

    @Autowired
    FacilityService facilityService;

    @Test
    void testCreateSingleActivityByFacilityByTime() {

        String facilityName = "Swimming Pool";
        Facility facility=facilityService.getFacilityByName(facilityName);
        LocalDateTime localDateTime = LocalDateTime.of(2022,3,20,10,0);
        Date date= singleOrderService.toDate(localDateTime);
        singleOrderService.createSingleActivityByFacilityByTime(facility, date);

    }
}