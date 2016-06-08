package com.wladek.pakmart.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

/**
 * Created by wladek on 6/8/16.
 */
@Entity
public class Buying extends AbstractModel {
    private BigDecimal amount;
    private BigDecimal costPerPoint;
    @ManyToOne
    private Customer customer;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getCostPerPoint() {
        return costPerPoint;
    }

    public void setCostPerPoint(BigDecimal costPerPoint) {
        this.costPerPoint = costPerPoint;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
