package com.wladek.pakmart.repository;

import com.wladek.pakmart.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wladek on 5/17/16.
 */
@Repository
public interface CardRepo extends JpaRepository<Card , Long>{
}
