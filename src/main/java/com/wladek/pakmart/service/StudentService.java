package com.wladek.pakmart.service;

import com.wladek.pakmart.domain.School;
import com.wladek.pakmart.domain.Student;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by wladek on 5/17/16.
 */
public interface StudentService {
    public Student create(Student student);
    public Student getOne(Long id);
    public List<Student> findAll();
    public Page<Student> findBySchool(int page , int size , School school);
}
