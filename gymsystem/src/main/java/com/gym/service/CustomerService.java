package com.gym.service;

import com.gym.mbg.model.Customer;
import com.gym.mbg.model.PmsBrand;

import java.util.List;

public interface CustomerService {

    int getMaleSize();

    int getFemaleSize();

    List<Customer> listAllCustomer();

    Customer getCustomer(int id);

    List<Integer> getRecentMonth(int monthNum);
}
