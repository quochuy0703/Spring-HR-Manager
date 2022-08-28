package com.example.springdemo.dao;

import java.util.List;

import com.example.springdemo.entity.WorkHour;

public interface WorkHourDAO {
    public List<WorkHour> findWorkHourByUserId(int theUserId);
    public void saveWorkHour(WorkHour workHour);
    public List<WorkHour> findWorkHourByUserIdAndByMonth(int theUserId, String month, String year);
    public void deleteWorkHourById(int theWorkHourId);
    public WorkHour findWorkHourById(int theWorkHourId);
}
