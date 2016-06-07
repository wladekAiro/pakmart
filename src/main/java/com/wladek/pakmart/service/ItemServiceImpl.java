package com.wladek.pakmart.service;

import com.wladek.pakmart.domain.Item;
import com.wladek.pakmart.domain.School;
import com.wladek.pakmart.repository.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wladek on 5/17/16.
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemRepo itemRepo;

    @Override
    public Item create(Item item) {
        return itemRepo.save(item);
    }

    @Override
    public Item getOne(Long id) {
        return itemRepo.getOne(id);
    }

    @Override
    public List<Item> listItems() {
        return itemRepo.findAll();
    }

    @Override
    public Page<Item> findBySchool(School school, int page, int size) {
        page = page - 1;
        PageRequest pageRequest = new PageRequest(page ,size);
        return itemRepo.findBySchool(pageRequest , school);
    }
}
