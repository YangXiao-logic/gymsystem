package com.gym.service.impl;

import com.gym.mbg.mapper.SingleOrderMapper;
import com.gym.mbg.model.Customer;
import com.gym.mbg.model.SingleOrder;
import com.gym.mbg.model.SingleOrderExample;
import com.gym.service.CustomerService;
import com.gym.service.SingleOrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SingleOrderServiceImpl implements SingleOrderService {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private SingleOrderMapper singleOrderMapper;


    @Override
    public List<SingleOrder> currentCustomerTotalOrder() {
        Customer customer= customerService.getCurrentCustomer();
        SingleOrderExample example=new SingleOrderExample();
        example.createCriteria().andUserIdEqualTo(customer.getId());
        return singleOrderMapper.selectByExample(example);
    }




}
