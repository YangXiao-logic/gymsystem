package com.gym.service.impl;

import com.gym.service.PmsBrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class PmsBrandServiceImplTest {

    @Autowired
    PmsBrandService pmsBrandService;

    @Test
    void listAllBrand() {
        System.out.println(pmsBrandService.listAllBrand());
    }
}