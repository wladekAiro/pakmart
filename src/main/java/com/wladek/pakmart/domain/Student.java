package com.wladek.pakmart.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * Created by wladek on 1/1/10.
 */
@Entity
public class Student extends AbstractModel{
    @NotEmpty(message = " Provide first name")
    private String firstName;
    @NotEmpty(message = " Provide last name")
    private String lastName;
    @NotEmpty(message = " Provide sir name")
    private String sirName;
    @NotEmpty(message = " Provide registration number")
    private String regNumber;

    @OneToOne(mappedBy = "student" , fetch = FetchType.LAZY)
    private Card card;

    @ManyToOne
    private School school;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSirName() {
        return sirName;
    }

    public void setSirName(String sirName) {
        this.sirName = sirName;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}
