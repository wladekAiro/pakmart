package com.wladek.pakmart.service;

import com.wladek.pakmart.domain.BuyingPointCost;
import com.wladek.pakmart.domain.SellingPointCost;

/**
 * Created by wladek on 6/8/16.
 */
public interface PointsService {
    public BuyingPointCost setBuyingCost(BuyingPointCost buyingPointCost);
    public SellingPointCost setSellingCost(SellingPointCost sellingPointCost);
}
