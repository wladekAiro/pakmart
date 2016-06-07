package com.wladek.pakmart.repository;

import com.wladek.pakmart.domain.School;
import com.wladek.pakmart.domain.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wladek on 5/17/16.
 */
@Repository
public interface StudentRepo extends JpaRepository<Student, Long>{
    public Page<Student> findBySchool(Pageable pageable , School school);
}
