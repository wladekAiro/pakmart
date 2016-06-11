package com.wladek.pakmart.repository;

import com.wladek.pakmart.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wladek on 6/8/16.
 */
@Repository
public interface CustomerSearchRepo extends JpaRepository<Customer , Long>{
    public Page<Customer> findByPhoneNumber(Pageable pageable , String phoneNumber);
}
