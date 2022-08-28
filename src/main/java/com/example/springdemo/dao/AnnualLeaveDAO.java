package com.example.springdemo.dao;

import java.util.List;

import com.example.springdemo.entity.AnnualLeave;

public interface AnnualLeaveDAO {
    public void saveAnnualLeave(AnnualLeave annualLeave);
    public List<AnnualLeave> findAnnualLeaveById(int theId);
    public List<AnnualLeave> findAnnualLeaveByIdAndByMonth(int theId, String month, String year);
}
