package com.wladek.pakmart.service;

import com.wladek.pakmart.domain.Buying;

import java.util.List;

/**
 * Created by wladek on 5/17/16.
 */
public interface BuyingService {
    public Buying makeBuy(Buying Buying);
    public Buying getBuy(Long id);
    public List<Buying> getAll();
}
