package com.gym.service.impl;

import com.gym.mbg.mapper.CustomerMapper;
import com.gym.mbg.model.Customer;
import com.gym.mbg.model.CustomerExample;
import com.gym.mbg.model.PmsBrand;
import com.gym.mbg.model.SingleOrder;
import com.gym.service.CustomerService;
import com.gym.service.SingleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private SingleOrderService singleOrderService;

    @Override
    public int getMaleSize() {

        CustomerExample example = new CustomerExample();
        example.createCriteria().andGenderEqualTo(1);
        List<Customer> customers = customerMapper.selectByExample(example);
        if (customers != null && customers.size()>0){;
            return customers.size();
        }
        return -1;
    }

    @Override
    public int getFemaleSize() {
        CustomerExample example = new CustomerExample();
        example.createCriteria().andGenderEqualTo(0);
        List<Customer> customers = customerMapper.selectByExample(example);
        if (customers != null && customers.size()>0){;
            return customers.size();
        }
        return -1;
    }

    @Override
    public List<Customer> listAllCustomer() {
        return customerMapper.selectByExample(new CustomerExample());
    }

    @Override
    public Customer getCustomer(int id) {
        return customerMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Integer> getRecentMonth(int monthNum) {
        CustomerExample customerExample=new CustomerExample();
        LocalDateTime localDateTime=LocalDateTime.now().minusMonths(12);
        List<Integer> res = new ArrayList<>();
        for(int i=0;i<12;i++){
            customerExample.createCriteria().andCreateTimeGreaterThan(singleOrderService.toDate(localDateTime))
                    .andCreateTimeLessThanOrEqualTo(singleOrderService.toDate(localDateTime));
            res.add((int)customerMapper.countByExample(customerExample));
            localDateTime.plusMonths(1);
        }
        return res;
    }


}
