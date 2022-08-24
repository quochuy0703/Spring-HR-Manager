package com.example.springdemo;

import java.util.ArrayList;
import java.util.Date;

import com.example.springdemo.entity.WorkHour;

public class WorkHourSession {
    private Date dateSession;
    private ArrayList<WorkHour> sessionWorkHour = new ArrayList<>();
    private long sumWorkHour;
    private String stringSumWorkHour;
    private long overTime;
    private String stringOverTime;
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
    public long getOverTime() {
        return overTime;
    }
    public void setOverTime(long overTime) {
        this.overTime = overTime;
    }
    public float getLeave() {
        return leave;
    }
    public void setLeave(float leave) {
        this.leave = leave;
    }
    public long getSumWorkHour() {
        return sumWorkHour;
    }
    public void setSumWorkHour(long sumWorkHour) {
        this.sumWorkHour = sumWorkHour;
    }
    public String getStringSumWorkHour() {
        return stringSumWorkHour;
    }
    public void setStringSumWorkHour(String stringSumWorkHour) {
        this.stringSumWorkHour = stringSumWorkHour;
    }
    public String getStringOverTime() {
        return stringOverTime;
    }
    public void setStringOverTime(String stringOverTime) {
        this.stringOverTime = stringOverTime;
    }
    @Override
    public String toString() {
        return "WorkHourSession [dateSession=" + dateSession + ", leave=" + leave + ", overTime=" + overTime
                + ", stringOverTime=" + stringOverTime + ", stringSumWorkHour=" + stringSumWorkHour + ", sumWorkHour="
                + sumWorkHour + "]";
    }

    

    
}
