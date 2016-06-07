package com.wladek.pakmart.service;

import com.wladek.pakmart.domain.School;
import com.wladek.pakmart.repository.SchoolRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wladek on 5/17/16.
 */
@Service
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    SchoolRepo schoolRepo;

    @Override
    public School create(School school) {
        return schoolRepo.save(school);
    }

    @Override
    public School getOne(Long id) {
        return schoolRepo.findOne(id);
    }

    @Override
    public List<School> getAll() {
        return schoolRepo.findAll();
    }

    @Override
    public Page<School> getAll(int page, int size) {
        page = page -1;
        PageRequest pageRequest = new PageRequest(page , size);
        return schoolRepo.findAll(pageRequest);
    }
}
