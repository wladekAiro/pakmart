package com.wladek.pakmart.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by wladek on 6/8/16.
 */
@Entity
public class Customer extends AbstractModel {
    @NotEmpty(message = "Provide first name")
    private String firstName;
    @NotEmpty(message = "Provide second name")
    private String secondName;
    @NotEmpty(message = "Provide sir name")
    private String sirName;
    @NotEmpty(message = "Provide id number")
    @Column(unique = true)
    private String idNumber;
    @NotEmpty(message = "Provide phone number")
    @Column(unique = true)
    private String phoneNumber;
    private BigDecimal points = new BigDecimal(0);

    @OneToMany(mappedBy = "customer" ,cascade = {CascadeType.REMOVE,CascadeType.PERSIST} , fetch = FetchType.LAZY)
    private Set<Buying> buyingSet;

    @OneToMany(mappedBy = "customer" ,cascade = {CascadeType.REMOVE,CascadeType.PERSIST} , fetch = FetchType.LAZY)
    private Set<Redeem> redeemSet;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getSirName() {
        return sirName;
    }

    public void setSirName(String sirName) {
        this.sirName = sirName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public BigDecimal getPoints() {
        return points;
    }

    public void setPoints(BigDecimal points) {
        this.points = points;
    }

    public String getFullName(){
        return firstName+" "+secondName+" "+sirName;
    }

    public Set<Buying> getBuyingSet() {
        return buyingSet;
    }

    public void setBuyingSet(Set<Buying> buyingSet) {
        this.buyingSet = buyingSet;
    }

    public Set<Redeem> getRedeemSet() {
        return redeemSet;
    }

    public void setRedeemSet(Set<Redeem> redeemSet) {
        this.redeemSet = redeemSet;
    }
}
