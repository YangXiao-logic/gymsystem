package com.gym.service.impl;

import com.gym.service.CustomerService;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class CustomerServiceImplTest extends TestCase {

    @Autowired
    CustomerService customerService;

    @Test
    public void testGetMaleRatio() {
        System.out.println(customerService.getMaleSize());
    }

    @Test
    public void testGetFemaleRatio() {
        System.out.println(customerService.getFemaleSize());
    }
}