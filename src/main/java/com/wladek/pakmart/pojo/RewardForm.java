package com.wladek.pakmart.pojo;

import java.math.BigDecimal;

/**
 * Created by wladek on 6/12/16.
 */

/**
 * Hold the values for points reward and redeeming simulation
 */
public class RewardForm {
    private String phoneNumber;
    private BigDecimal amount;
    private Long id;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
