package com.wladek.pakmart.service;

import com.wladek.pakmart.domain.School;
import com.wladek.pakmart.domain.Student;
import com.wladek.pakmart.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wladek on 5/17/16.
 */
@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    StudentRepo studentRepo;
    @Override
    public Student create(Student student) {
        return studentRepo.save(student);
    }

    @Override
    public Student getOne(Long id) {
        return studentRepo.getOne(id);
    }

    @Override
    public List<Student> findAll() {
        return studentRepo.findAll();
    }

    @Override
    public Page<Student> findBySchool(int page , int size , School school) {

        page = page - 1;

        PageRequest pageRequest = new PageRequest(page , size);

        return studentRepo.findBySchool(pageRequest , school);
    }
}
