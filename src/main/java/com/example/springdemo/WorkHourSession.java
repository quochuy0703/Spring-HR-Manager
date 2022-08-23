package com.example.springdemo;

import java.util.ArrayList;
import java.util.Date;

import com.example.springdemo.entity.WorkHour;

public class WorkHourSession {
    private Date dateSession;
    private ArrayList<WorkHour> sessionWorkHour = new ArrayList<>();
    private Date sumWorkHour;
    private Date overTime;
    private float leave;
    public WorkHourSession() {

    }
    public Date getDateSession() {
        return dateSession;
    }
    public void setDateSession(Date dateSession) {
        this.dateSession = dateSession;
    }
    public ArrayList<WorkHour> getSessionWorkHour() {
        return sessionWorkHour;
    }
    public void setSessionWorkHour(ArrayList<WorkHour> sessionWorkHour) {
        this.sessionWorkHour = sessionWorkHour;
    }
    public Date getOverTime() {
        return overTime;
    }
    public void setOverTime(Date overTime) {
        this.overTime = overTime;
    }
    public float getLeave() {
        return leave;
    }
    public void setLeave(float leave) {
        this.leave = leave;
    }
    public Date getSumWorkHour() {
        return sumWorkHour;
    }
    public void setSumWorkHour(Date sumWorkHour) {
        this.sumWorkHour = sumWorkHour;
    }

    
}
