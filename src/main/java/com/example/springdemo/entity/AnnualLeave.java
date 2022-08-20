package com.example.springdemo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="annual_leave")
public class AnnualLeave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="start_date_leave")
    private Date startDate;

    @Column(name="is_morning_start_date")
    private boolean isMorningStartDate;

    @Column(name="end_date_leave")
    private Date endDate;
    @Column(name="is_morning_end_date")
    private boolean isMorningEndDate;

    @Column(name="reason_leave")
    private String reasonLeave;

    @Column(name="count_leave")
    private int countDay;

    

    

    public AnnualLeave() {
    }

    public AnnualLeave(Date startDate, boolean isMorningStartDate, Date endDate, boolean isMorningEndDate,
            String reasonLeave, int countDay) {
        this.startDate = startDate;
        this.isMorningStartDate = isMorningStartDate;
        this.endDate = endDate;
        this.isMorningEndDate = isMorningEndDate;
        this.reasonLeave = reasonLeave;
        this.countDay = countDay;
    }

    @Override
    public String toString() {
        return "AnnualLeave [countDay=" + countDay + ", endDate=" + endDate + ", id=" + id + ", isMorningEndDate="
                + isMorningEndDate + ", isMorningStartDate=" + isMorningStartDate + ", reasonLeave=" + reasonLeave
                + ", startDate=" + startDate + "]";
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

    public boolean isMorningStartDate() {
        return isMorningStartDate;
    }

    public void setMorningStartDate(boolean isMorningStartDate) {
        this.isMorningStartDate = isMorningStartDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isMorningEndDate() {
        return isMorningEndDate;
    }

    public void setMorningEndDate(boolean isMorningEndDate) {
        this.isMorningEndDate = isMorningEndDate;
    }

    public String getReasonLeave() {
        return reasonLeave;
    }

    public void setReasonLeave(String reasonLeave) {
        this.reasonLeave = reasonLeave;
    }

    public int getCountDay() {
        return countDay;
    }

    public void setCountDay(int countDay) {
        this.countDay = countDay;
    }


    
}
