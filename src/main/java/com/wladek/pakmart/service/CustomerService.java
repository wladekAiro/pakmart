package com.wladek.pakmart.service;

import com.wladek.pakmart.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by wladek on 6/8/16.
 */
@Service
public interface CustomerService {
    public Customer create(Customer customer);
    public Page<Customer> findAll(int page , int size);
    public Customer findByPhoneNumber(String phoneNumber);
    public Customer findByIdNumber(String idNumber);
    public String redeemPoints(String phoneNumber , BigDecimal points);
    public String buyPoints(String phoneNumber, BigDecimal goodWorth);
}
