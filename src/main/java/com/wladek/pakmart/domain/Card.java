package com.wladek.pakmart.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by wladek on 1/1/10.
 */
@Entity
public class Card extends AbstractModel{
    private String cardNo;
    private BigDecimal balance;

    @OneToMany(mappedBy = "card")
    private Set<Buying> buyingSet;

    @OneToMany(mappedBy = "card")
    private Set<CardTransaction> transactions;

    @OneToOne(fetch = FetchType.LAZY)
    private Student student;

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Set<Buying> getBuyingSet() {
        return buyingSet;
    }

    public void setBuyingSet(Set<Buying> buyingSet) {
        this.buyingSet = buyingSet;
    }

    public Set<CardTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<CardTransaction> transactions) {
        this.transactions = transactions;
    }
}
