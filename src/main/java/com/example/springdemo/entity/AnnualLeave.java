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
@Table(name="annual_leave")
public class AnnualLeave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="start_date_leave")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @Column(name="is_morning_start_date")
    private boolean morningStartDate;

    @Column(name="end_date_leave")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @Column(name="is_morning_end_date")
    private boolean morningEndDate;

    @Column(name="reason_leave")
    private String reasonLeave;

    @Column(name="count_leave")
    private float countDay;

    @ManyToOne(cascade = { CascadeType.MERGE})
    @JoinColumn(name="user_id")
    private User user;

    

    public AnnualLeave() {
    }

    

   

    @Override
    public String toString() {
        return "AnnualLeave [countDay=" + countDay + ", endDate=" + endDate + ", morningEndDate=" + morningEndDate
                + ", morningStartDate=" + morningStartDate + ", reasonLeave=" + reasonLeave + ", startDate=" + startDate
                + "]";
    }





    public boolean isMorningStartDate() {
        return morningStartDate;
    }





    public void setMorningStartDate(boolean morningStartDate) {
        this.morningStartDate = morningStartDate;
    }





    public boolean isMorningEndDate() {
        return morningEndDate;
    }





    public void setMorningEndDate(boolean morningEndDate) {
        this.morningEndDate = morningEndDate;
    }





    public AnnualLeave(Date startDate, boolean morningStartDate, Date endDate, boolean morningEndDate,
            String reasonLeave, int countDay) {
        this.startDate = startDate;
        this.morningStartDate = morningStartDate;
        this.endDate = endDate;
        this.morningEndDate = morningEndDate;
        this.reasonLeave = reasonLeave;
        this.countDay = countDay;
    }





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

   

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

   

    public String getReasonLeave() {
        return reasonLeave;
    }

    public void setReasonLeave(String reasonLeave) {
        this.reasonLeave = reasonLeave;
    }

    public float getCountDay() {
        return countDay;
    }

    public void setCountDay(float countDay) {
        this.countDay = countDay;
    }





    public User getUser() {
        return user;
    }





    public void setUser(User user) {
        this.user = user;
    }


    
}
