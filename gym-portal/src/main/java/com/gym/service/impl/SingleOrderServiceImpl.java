package com.gym.service.impl;

import com.gym.common.api.CommonResult;
import com.gym.mbg.mapper.ActivityMapper;
import com.gym.mbg.mapper.CustomerMapper;
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
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private ActivityMapper activityMapper;


    public int pay(int price){
        Integer balance = customerService.getCurrentCustomer().getBalance();
        if(balance<price){
            return 0;
        }
        customerService.getCurrentCustomer().setBalance(balance-price);
        customerMapper.updateByPrimaryKeySelective(customerService.getCurrentCustomer());
        return 1;
    }

    public int refund(int price){
        Integer balance = customerService.getCurrentCustomer().getBalance();
        customerService.getCurrentCustomer().setBalance(balance+price);
        customerMapper.updateByPrimaryKeySelective(customerService.getCurrentCustomer());
        return 1;
    }


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
        int price = activityMapper.selectByPrimaryKey(singleOrder.getActivityId()).getPrice();
        int count=pay(price);
        if(count==0){
            return 2;
        }
        return singleOrderMapper.insertSelective(singleOrder);
    }

    @Override
    public int cancel(int facility_id, Date date) {
        SingleOrderExample singleOrderExample=new SingleOrderExample();
        singleOrderExample.createCriteria().andUserIdEqualTo(customerService.getCurrentCustomer().getId())
                .andFacilityIdEqualTo(facility_id).andStartTimeEqualTo(date);

        List<SingleOrder> singleOrderList = singleOrderMapper.selectByExample(singleOrderExample);
        if(singleOrderList.size()==1){
            SingleOrder singleOrder = singleOrderList.get(0);
            int price = activityMapper.selectByPrimaryKey(singleOrder.getActivityId()).getPrice();
            refund(price);
        }
        return singleOrderMapper.deleteByExample(singleOrderExample);
    }


}
