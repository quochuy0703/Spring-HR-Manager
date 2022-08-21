package com.example.springdemo.entity;



import java.lang.management.MemoryType;
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
@Table(name="temp")
public class Temp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="temp")
    private float temp;

    @Column(name="date_temp")
    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
    private Date dateTemp;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name="user_id")
    private User user;


    @Override
    public String toString() {
        return "Temp [dateTemp=" + dateTemp + ", id=" + id + ", temp=" + temp + "]";
    }

    public Temp() {
    }

    public Temp(float temp, Date dateTemp) {
        this.temp = temp;
        this.dateTemp = dateTemp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public Date getDateTemp() {
        return dateTemp;
    }

    public void setDateTemp(Date dateTemp) {
        this.dateTemp = dateTemp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    

    
}
