package com.example.springdemo.dao;

import java.util.List;

import com.example.springdemo.entity.WorkHour;

public interface WorkHourDAO {
    public List<WorkHour> findWorkHourByUserId(int theUserId);
    public void saveWorkHour(WorkHour workHour);
}
