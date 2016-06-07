package com.wladek.pakmart.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;

/**
 * Created by wladek on 1/1/10.
 */
@Entity
public class School extends AbstractModel{
    @NotEmpty(message = "Provide school name")
    private String name;
    @NotEmpty(message = "Provide school code")
    private String code;

    @OneToMany(mappedBy = "school" , fetch = FetchType.LAZY)
    private Set<Student> students;

    @OneToMany(mappedBy = "school" , fetch = FetchType.LAZY)
    private Set<Item> items;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}
