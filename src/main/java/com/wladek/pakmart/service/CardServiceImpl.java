package com.wladek.pakmart.service;

import com.wladek.pakmart.domain.Card;
import com.wladek.pakmart.repository.CardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wladek on 5/17/16.
 */
@Service
public class CardServiceImpl implements CardService{
    @Autowired
    CardRepo cardRepo;
    @Override
    public Card create(Card card) {
        return cardRepo.save(card);
    }

    @Override
    public Card getOne(Long id) {
        return cardRepo.getOne(id);
    }

    @Override
    public List<Card> findAll() {
        return cardRepo.findAll();
    }
}
