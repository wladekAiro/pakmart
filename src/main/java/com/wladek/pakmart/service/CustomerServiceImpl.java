package com.wladek.pakmart.service;

import com.wladek.pakmart.domain.Buying;
import com.wladek.pakmart.domain.Customer;
import com.wladek.pakmart.domain.Redeem;
import com.wladek.pakmart.domain.enumeration.PointCostStatus;
import com.wladek.pakmart.repository.BuyingPointsRepo;
import com.wladek.pakmart.repository.CustomerRepo;
import com.wladek.pakmart.repository.CustomerSearchRepo;
import com.wladek.pakmart.repository.RedeemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by wladek on 6/8/16.
 */
@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    PointsService pointsService;
    @Autowired
    BuyingPointsRepo buyingPointsRepo;
    @Autowired
    RedeemRepo redeemRepo;
    @Autowired
    CustomerSearchRepo customerSearchRepo;

    @Override
    public Customer create(Customer customer) {
        return customerRepo.save(customer);
    }

    @Override
    public Customer findOne(Long id) {
        return customerRepo.findOne(id);
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
        Customer customerInDb = findByPhoneNumber(phoneNumber);

        if (customerInDb == null){
            return "Phone number not registered";
        }

        BigDecimal customerPoints = customerInDb.getPoints();

        if(customerPoints.compareTo(points) < 1){
            return "Not enough points. Your point balance is "+customerPoints.toBigIntegerExact();
        }

        customerPoints = customerPoints.subtract(points);

        Redeem redeem = new Redeem();
        redeem.setCustomer(customerInDb);
        redeem.setPoints(points);

        BigDecimal redeemCost = pointsService.getActiveSellingPointCost(PointCostStatus.ACTIVE).getCost();
        BigDecimal redeemWorth = points.multiply(redeemCost);

        redeem.setShillingPerPoint(redeemCost);
        redeem.setValue(redeemWorth);
        redeemRepo.save(redeem);

        customerInDb.setPoints(customerPoints);
        customerRepo.save(customerInDb);

        return generateMessage(customerInDb , points , redeemWorth);
    }

    @Override
    public Page<Customer> findByPhoneNumberPaged(String phoneNumber) {
        PageRequest pageRequest = new PageRequest(0 , 1);
        return customerSearchRepo.findByPhoneNumber(pageRequest , phoneNumber);
    }

    private String generateMessage(Customer customerInDb, BigDecimal points, BigDecimal redeemWorth) {

        String message = " You have successfully redeemed "
                +points.toBigIntegerExact() +" worth KSH "+redeemWorth
                +". Your points balance is "+customerInDb.getPoints().toBigIntegerExact();

        return message;
    }

    @Override
    public String buyPoints(String phoneNumber, BigDecimal goodWorth) {

        Customer customerInDb = findByPhoneNumber(phoneNumber);

        if (customerInDb == null){
            return "Phone number not registered";
        }

        String response = rewardPoints(customerInDb , goodWorth);

        return response;
    }

    private String rewardPoints(Customer customerInDb, BigDecimal goodWorth) {

        BigDecimal buyingCost = pointsService.getActiveBuyingPointCost(PointCostStatus.ACTIVE).getCost();

        Buying buying = new Buying();
        buying.setAmount(goodWorth);
        buying.setCostPerPoint(buyingCost);
        buying.setCustomer(customerInDb);

        BigDecimal points = (goodWorth.divide(buyingCost));

        customerInDb.setPoints(customerInDb.getPoints().add(points));
        customerRepo.save(customerInDb);

        return customerInDb.getFullName()+" awarded "+points.intValue();
    };
}
