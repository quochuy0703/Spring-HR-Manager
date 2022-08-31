package com.example.springdemo.service;

import java.util.List;

import com.example.springdemo.entity.AnnualLeave;
import com.example.springdemo.entity.Covid;
import com.example.springdemo.entity.Injection;
import com.example.springdemo.entity.Temp;
import com.example.springdemo.entity.User;
import com.example.springdemo.entity.WorkHour;

public interface MultiService {
    public User findUserById(int theId);
    public User findUserByEmail(String email);
    public List<User> findEmployeeByDepartment(String department);
    
    public void saveWorkHour(WorkHour workHour) ;
    public List<WorkHour> findWorkHourByUserIdAndByMonth(int theUserId, String month, String year);
    public void deleteWorkHourById(int theWorkHourId);
    public WorkHour findWorkHourById(int theWorkHourId);
    public void saveUser(User theUser);

    public List<AnnualLeave> findAnnualLeaveById(int theId);
    public List<AnnualLeave> findAnnualLeaveByIdAndByMonth(int theId, String month, String year);
    public void saveAnnualLeave(AnnualLeave annualLeave);
    public List<AnnualLeave> findAnnualLeaveByUserId(int theUserId);

    public List<Temp> findTempByUserId(int userId);

    public void saveTemp(Temp theTemp);

    public void saveInjection(Injection injection);

    public void saveCovid(Covid covid);
}
