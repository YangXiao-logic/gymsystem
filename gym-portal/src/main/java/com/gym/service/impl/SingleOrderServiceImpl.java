package com.gym.service.impl;

import com.gym.common.api.CommonResult;
import com.gym.mbg.mapper.SingleOrderMapper;
import com.gym.mbg.model.Customer;
import com.gym.mbg.model.SingleOrder;
import com.gym.mbg.model.SingleOrderExample;
import com.gym.service.CustomerService;
import com.gym.service.SingleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
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

    @Override
    public int order(SingleOrder singleOrder) {
        singleOrder.setUserId(customerService.getCurrentCustomer().getId());
        return singleOrderMapper.insertSelective(singleOrder);
    }

    @Override
    public int cancel(int facility_id, Date date) {
        SingleOrderExample singleOrderExample=new SingleOrderExample();
        singleOrderExample.createCriteria().andUserIdEqualTo(customerService.getCurrentCustomer().getId())
                .andFacilityIdEqualTo(facility_id).andStartTimeEqualTo(date);
        return singleOrderMapper.deleteByExample(singleOrderExample);
    }


}
