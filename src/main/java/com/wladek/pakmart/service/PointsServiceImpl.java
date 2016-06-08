package com.wladek.pakmart.service;

import com.wladek.pakmart.domain.BuyingPointCost;
import com.wladek.pakmart.domain.SellingPointCost;
import com.wladek.pakmart.domain.enumeration.PointCostStatus;
import com.wladek.pakmart.repository.BuyingPointsRepo;
import com.wladek.pakmart.repository.SellingPointsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wladek on 6/8/16.
 */
@Service
public class PointsServiceImpl implements PointsService{
    @Autowired
    BuyingPointsRepo buyingPointsRepo;
    @Autowired
    SellingPointsRepo sellingPointsRepo;

    @Override
    public BuyingPointCost setBuyingCost(BuyingPointCost buyingPointCost) {

        BuyingPointCost costInDb = getActiveBuyingPointCost(PointCostStatus.ACTIVE);

        if (costInDb != null){
            costInDb.setStatus(PointCostStatus.IN_ACTIVE);
            updateBuying(costInDb);
        }

        return buyingPointsRepo.save(buyingPointCost);
    }

    @Override
    public SellingPointCost setSellingCost(SellingPointCost sellingPointCost) {

        SellingPointCost costInDb = getActiveSellingPointCost(PointCostStatus.ACTIVE);

        if(costInDb != null){
            costInDb.setStatus(PointCostStatus.IN_ACTIVE);
            updateSelling(costInDb);
        }

        return sellingPointsRepo.save(sellingPointCost);
    }

    @Override
    public BuyingPointCost getActiveBuyingPointCost(PointCostStatus status) {
        return buyingPointsRepo.findByStatus(status);
    }

    @Override
    public SellingPointCost getActiveSellingPointCost(PointCostStatus status) {
        return sellingPointsRepo.findByStatus(status);
    }

    public BuyingPointCost updateBuying(BuyingPointCost buyingPointCost){
        return buyingPointsRepo.save(buyingPointCost);
    }

    public SellingPointCost updateSelling(SellingPointCost sellingPointCost){
        return sellingPointsRepo.save(sellingPointCost);
    }
}
