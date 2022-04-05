package com.gym.controller;


import com.gym.common.api.CommonResult;
import com.gym.mbg.model.Customer;
import com.gym.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(tags = "CustomerController", description = "用户管理")
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @ApiOperation("获取男性用户数量")
    @GetMapping("male")
    public CommonResult<Integer> getMale(){
        return CommonResult.success(customerService.getMaleSize());
    }

    @ApiOperation("获取女性用户数量")
    @GetMapping("female")
    public CommonResult<Integer> getFemale(){
        return CommonResult.success(customerService.getFemaleSize());
    }

    @ApiOperation("获取用户列表")
    @GetMapping
    public CommonResult<List<Customer>> getCustomerList(){
        return CommonResult.success(customerService.listAllCustomer());
    }

    @ApiOperation("按id获取用户")
    @GetMapping("/{id}")
    public CommonResult<Customer> getCustomer(@PathVariable("id") int id){
        return CommonResult.success(customerService.getCustomer(id));
    }


}
