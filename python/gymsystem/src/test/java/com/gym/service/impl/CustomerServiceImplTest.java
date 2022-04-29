package com.gym.service.impl;

import com.gym.service.CustomerService;
import junit.framework.TestCase;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerServiceImplTest extends TestCase {

    @Autowired
    CustomerService customerService;

    public void testGetMaleRatio() {
        System.out.println(customerService.getMaleSize());
    }

    public void testGetFemaleRatio() {
        System.out.println(customerService.getFemaleSize());
    }
}