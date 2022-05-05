package com.gym.service;

import com.gym.mbg.model.Customer;

public interface CustomerService {

    Customer getCustomerByUsername(String username);

    Customer register(Customer customerParam);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username, String password);

    Customer getCurrentCustomer();


    Customer changeInfo(Customer customerParam);
}
