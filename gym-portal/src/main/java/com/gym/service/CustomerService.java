package com.gym.service;

import com.gym.mbg.model.Customer;

public interface CustomerService {

    Customer getCustomerByUsername(String username);

    Customer getCustomerByPhone(String phone);

    Boolean hasPhone(String phone);

    Customer register(Customer customerParam);

    Customer newPassword(String phone, String newPassword);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username, String password);

    String loginByPhone(String phone);

    Customer getCurrentCustomer();


    Customer changeInfo(Customer customerParam);

    int logOff();

    int charge(int money);
}
