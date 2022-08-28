package com.example.springdemo;

public class SalaryForm {
    public String month;
    public String year;
    public String getMonth() {
        return month;
    }
    public void setMonth(String month) {
        this.month = month;
    }
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    @Override
    public String toString() {
        return "SalaryForm [month=" + month + ", year=" + year + "]";
    }
    
}
