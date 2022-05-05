package com.gym.service.impl;

import com.gym.common.utils.JwtTokenUtil;
import com.gym.dto.CustomerUserDetails;
import com.gym.mbg.mapper.CustomerMapper;
import com.gym.mbg.model.Customer;
import com.gym.mbg.model.CustomerExample;
import com.gym.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private CustomerMapper customerMapper;


    @Override
    public Customer getCustomerByUsername(String username) {
        CustomerExample example = new CustomerExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<Customer> customerList = customerMapper.selectByExample(example);
        if (customerList != null && customerList.size() > 0) {
            return customerList.get(0);
        }
        return null;
    }

    @Override
    public Customer register(Customer customerParam) {

        Customer customer = new Customer();
        BeanUtils.copyProperties(customerParam, customer);
        customer.setCreateTime(new Date());
        customer.setStatus(1);
        //查询是否有相同用户名的用户
        CustomerExample example = new CustomerExample();
        example.createCriteria().andUsernameEqualTo(customer.getUsername());
        List<Customer> customerList = customerMapper.selectByExample(example);
        if (customerList.size() > 0) {
            return null;
        }
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodePassword);
        customerMapper.insert(customer);
        return customer;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    @Override
    public Customer getCurrentCustomer() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        CustomerUserDetails customerUserDetails = (CustomerUserDetails) auth.getPrincipal();
        return customerUserDetails.getCustomer();
    }

    @Override
    public Customer changeInfo(Customer customerParam) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerParam, customer);
        customer.setId(getCurrentCustomer().getId());
        customerMapper.updateByPrimaryKeySelective(customer);
        return customer;
    }


}
