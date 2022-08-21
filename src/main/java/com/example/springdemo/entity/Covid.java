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

@Entity
@Table(name="covid")
public class Covid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="covid_info")
    private Boolean covidInfo;

    @Column(name="date_covid")
    private Date dateCovid;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="user_id")
    private User user;

    public Covid() {
    }

    public Covid(Boolean covidInfo, Date dateCovid) {
        this.covidInfo = covidInfo;
        this.dateCovid = dateCovid;
    }

    

    @Override
    public String toString() {
        return "Covid [covidInfo=" + covidInfo + ", dateCovid=" + dateCovid + ", id=" + id + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getCovidInfo() {
        return covidInfo;
    }

    public void setCovidInfo(Boolean covidInfo) {
        this.covidInfo = covidInfo;
    }

    public Date getDateCovid() {
        return dateCovid;
    }

    public void setDateCovid(Date dateCovid) {
        this.dateCovid = dateCovid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    
    
}
