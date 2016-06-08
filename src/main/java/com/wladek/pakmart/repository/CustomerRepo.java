package com.wladek.pakmart.repository;

import com.wladek.pakmart.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wladek on 6/8/16.
 */
@Repository
public interface CustomerRepo extends JpaRepository<Customer , Long>{
    public Customer findByIdNumber(String idNumber);
    public Customer findByPhoneNumber(String phoneNumber);
}
