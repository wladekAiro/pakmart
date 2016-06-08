package com.wladek.pakmart.service;

import com.wladek.pakmart.domain.Customer;
import com.wladek.pakmart.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;

/**
 * Created by wladek on 6/8/16.
 */
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    CustomerRepo customerRepo;

    @Override
    public Customer create(Customer customer) {
        return customerRepo.save(customer);
    }

    @Override
    public Page<Customer> findAll(int page, int size) {
        page = page -1;
        PageRequest pageRequest = new PageRequest(page , size);
        return customerRepo.findAll(pageRequest);
    }

    @Override
    public Customer findByPhoneNumber(String phoneNumber) {
        return customerRepo.findByPhoneNumber(phoneNumber);
    }

    @Override
    public Customer findByIdNumber(String idNumber) {
        return customerRepo.findByIdNumber(idNumber);
    }

    @Override
    public String redeemPoints(String phoneNumber, BigDecimal points) {
        return null;
    }

    @Override
    public String buyPoints(String phoneNumber, BigDecimal goodWorth) {
        return null;
    }
}
