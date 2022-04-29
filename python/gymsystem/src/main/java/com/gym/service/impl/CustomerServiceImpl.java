package com.gym.service.impl;

import com.gym.mbg.mapper.CustomerMapper;
import com.gym.mbg.model.Customer;
import com.gym.mbg.model.CustomerExample;
import com.gym.mbg.model.PmsBrand;
import com.gym.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

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


}
