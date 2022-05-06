package com.gym.controller;

import com.gym.common.api.CommonResult;
import com.gym.dto.CustomerLoginParam;

import com.gym.mbg.model.Customer;
import com.gym.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台用户管理
 * Created by macro on 2018/4/26.
 */
@Controller
@Api(tags = "CustomerController", description = "customer")
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Customer> register(@RequestBody Customer customerParam, BindingResult result) {
        Customer customer = customerService.register(customerParam);
        if (customer == null) {
            CommonResult.failed();
        }
        return CommonResult.success(customer);
    }

    @ApiOperation(value = "登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@RequestBody CustomerLoginParam customerLoginParam, BindingResult result) {
        String token = customerService.login(customerLoginParam.getUsername(), customerLoginParam.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "获取用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Customer> getCurrentCustomer(){
        return CommonResult.success(customerService.getCurrentCustomer());
    }

    @ApiOperation(value = "用户修改信息")
    @RequestMapping(value = "/changeInfo", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Customer> changeInfo(@RequestBody Customer customerParam, BindingResult result) {
        Customer customer = customerService.changeInfo(customerParam);
        if (customer == null) {
            CommonResult.failed();
        }
        return CommonResult.success(customer);
    }

    @RequestMapping(value = "/balance",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult charge(@RequestParam int money){
        int count=customerService.charge(money);
        if (count == 1) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("操作失败");
        }
    }


    @RequestMapping(value = "/logoff", method = RequestMethod.DELETE)
    @ResponseBody
    public CommonResult logOff(){
        int count=customerService.logOff();
        if (count == 1) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("操作失败");
        }
    }


}
