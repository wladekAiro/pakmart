package com.wladek.pakmart.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

/**
 * Created by wladek on 6/8/16.
 */
@Entity
public class Redeem extends AbstractModel{
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Customer customer;
    private BigDecimal points;
    private BigDecimal value;
    private BigDecimal shillingPerPoint;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getPoints() {
        return points;
    }

    public void setPoints(BigDecimal points) {
        this.points = points;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getShillingPerPoint() {
        return shillingPerPoint;
    }

    public void setShillingPerPoint(BigDecimal shillingPerPoint) {
        this.shillingPerPoint = shillingPerPoint;
    }
}
