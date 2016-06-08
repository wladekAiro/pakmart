package com.wladek.pakmart.service;

import com.wladek.pakmart.domain.BuyingPointCost;
import com.wladek.pakmart.domain.SellingPointCost;
import com.wladek.pakmart.repository.BuyingPointsRepo;
import com.wladek.pakmart.repository.SellingPointsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wladek on 6/8/16.
 */
public class PointsServiceImpl implements PointsService{
    @Autowired
    BuyingPointsRepo buyingPointsRepo;
    @Autowired
    SellingPointsRepo sellingPointsRepo;

    @Override
    public BuyingPointCost setBuyingCost(BuyingPointCost buyingPointCost) {
        return buyingPointsRepo.save(buyingPointCost);
    }

    @Override
    public SellingPointCost setSellingCost(SellingPointCost sellingPointCost) {
        return sellingPointsRepo.save(sellingPointCost);
    }
}
