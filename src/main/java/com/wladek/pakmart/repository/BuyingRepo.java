package com.wladek.pakmart.repository;

import com.wladek.pakmart.domain.Buying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wladek on 5/17/16.
 */
@Repository
public interface BuyingRepo extends JpaRepository<Buying , Long>{
}
