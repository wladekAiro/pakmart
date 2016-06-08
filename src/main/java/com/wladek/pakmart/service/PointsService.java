package com.wladek.pakmart.service;

import com.wladek.pakmart.domain.BuyingPointCost;
import com.wladek.pakmart.domain.SellingPointCost;
import com.wladek.pakmart.domain.enumeration.PointCostStatus;

/**
 * Created by wladek on 6/8/16.
 */
public interface PointsService {
    public BuyingPointCost setBuyingCost(BuyingPointCost buyingPointCost);
    public SellingPointCost setSellingCost(SellingPointCost sellingPointCost);
    public BuyingPointCost getActiveBuyingPointCost(PointCostStatus status);
    public SellingPointCost getActiveSellingPointCost(PointCostStatus status);
}
