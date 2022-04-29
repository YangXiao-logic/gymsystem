package com.gym.service.impl;

import com.gym.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class FacilityServiceImplTest {

    @Autowired
    FacilityService facilityService;

    @org.junit.jupiter.api.Test
    void getFacility() {
        System.out.println(facilityService.getFacility(2));
    }
}