package com.wladek.pakmart.domain;

import com.wladek.pakmart.domain.enumeration.PointCostStatus;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

/**
 * Created by wladek on 6/8/16.
 */
@Entity
public class BuyingPointCost extends AbstractModel{
    @NotNull(message = "Provide cost per point")
    private BigDecimal cost;
    private PointCostStatus status  = PointCostStatus.ACTIVE;


    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public PointCostStatus getStatus() {
        return status;
    }

    public void setStatus(PointCostStatus status) {
        this.status = status;
    }


    public String dateCreated(){
        SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yy");

        return format.format(getCreatedAt());
    }
}
