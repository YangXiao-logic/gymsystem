package com.gym.service.impl;

import com.gym.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerServiceImplTest {

    @Autowired
    private CustomerService customerService;

    @Test
    void getCustomerByUsername() {
    }

    @Test
    void register() {
    }

    @Test
    void login() {
        for(int i=0;i<500;i++){
            customerService.login("user"+i,"123456");
            
        }
    }

    @Test
    void getCurrentCustomer() {

    }

    @Test
    void changeInfo() {
    }
}