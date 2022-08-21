package com.example.springdemo.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="injection")
public class Injection {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="index_injection")
    private int index;

    @Column(name="type_vacxin")
    private int typeVacxin;

    @Column(name="date_injection")
    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
    private Date dateInjection;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name="user_id")
    private User user;


    public Injection() {
    }

    public Injection(int index, int typeVacxin, Date dateInjection) {
        this.index = index;
        this.typeVacxin = typeVacxin;
        this.dateInjection = dateInjection;
    }

    @Override
    public String toString() {
        return "Injection [dateInjection=" + dateInjection + ", id=" + id + ", index=" + index + ", typeVacxin="
                + typeVacxin + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getTypeVacxin() {
        return typeVacxin;
    }

    public void setTypeVacxin(int typeVacxin) {
        this.typeVacxin = typeVacxin;
    }

    public Date getDateInjection() {
        return dateInjection;
    }

    public void setDateInjection(Date dateInjection) {
        this.dateInjection = dateInjection;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    

}
