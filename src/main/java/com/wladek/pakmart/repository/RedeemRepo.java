package com.wladek.pakmart.repository;

import com.wladek.pakmart.domain.Redeem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wladek on 6/12/16.
 */
@Repository
public interface RedeemRepo extends JpaRepository<Redeem , Long> {
}
