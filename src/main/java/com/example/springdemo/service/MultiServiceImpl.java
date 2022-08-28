package com.example.springdemo.service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springdemo.dao.AnnualLeaveDAO;
import com.example.springdemo.dao.CovidRepository;
import com.example.springdemo.dao.InjectionRepository;
import com.example.springdemo.dao.TempDAO;
import com.example.springdemo.dao.UserDAO;
import com.example.springdemo.dao.WorkHourDAO;
import com.example.springdemo.entity.AnnualLeave;
import com.example.springdemo.entity.Covid;
import com.example.springdemo.entity.Injection;
import com.example.springdemo.entity.Temp;
import com.example.springdemo.entity.User;
import com.example.springdemo.entity.WorkHour;

@Service
public class MultiServiceImpl implements MultiService{

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private WorkHourDAO workHourDAO;

    @Autowired
    private AnnualLeaveDAO annualLeaveDAO;

    @Autowired
    private TempDAO tempDAO;

    @Autowired
    private InjectionRepository injectionRepository;

    @Autowired
    private CovidRepository covidRepository;

    @Override
    @Transactional
    public User findUserById(int theId) {
        
        return userDAO.findUserById(theId);
    }

    @Override
    @Transactional
    public void saveWorkHour(WorkHour workHour) {
        workHourDAO.saveWorkHour(workHour);
        
    }

    @Override
    @Transactional
    public void saveUser(User theUser) {
        userDAO.saveUser(theUser);
        
    }

    @Override
    @Transactional
    public List<AnnualLeave> findAnnualLeaveById(int theId) {
        
        return annualLeaveDAO.findAnnualLeaveById(theId);
    }

    @Override
    @Transactional
    public void saveAnnualLeave(AnnualLeave annualLeave) {
        
        annualLeaveDAO.saveAnnualLeave(annualLeave);
        
    }

    @Override
    @Transactional
    public List<Temp> findTempByUserId(int userId) {
        
        return this.tempDAO.findTempByUserId(userId);
    }

    @Override
    @Transactional
    public void saveTemp(Temp theTemp) {
       
        this.tempDAO.saveTemp(theTemp);
    }

    @Override
    @Transactional
    public void saveInjection(Injection injection) {
        this.injectionRepository.save(injection);
        
    }

    @Override
    @Transactional
    public void saveCovid(Covid covid) {
        this.covidRepository.save(covid);
        
    }

    @Override
    @Transactional
    public User findUserByEmail(String email) {
        
        return userDAO.findUserByEmail(email);
    }

    @Override
    @Transactional
    public List<User> findEmployeeByDepartment(String department) {
        
        return userDAO.findEmployeeByDepartment(department);
    }

    @Override
    @Transactional
    public List<WorkHour> findWorkHourByUserIdAndByMonth(int theUserId, String month, String year) {
        
        return workHourDAO.findWorkHourByUserIdAndByMonth(theUserId, month, year);
    }

    @Override
    @Transactional
    public List<AnnualLeave> findAnnualLeaveByIdAndByMonth(int theId, String month, String year) {
        
        return annualLeaveDAO.findAnnualLeaveByIdAndByMonth(theId, month, year);
    }

    @Override
    @Transactional
    public void deleteWorkHourById(int theWorkHourId) {
        workHourDAO.deleteWorkHourById(theWorkHourId);
        
    }

    @Override
    @Transactional
    public WorkHour findWorkHourById(int theWorkHourId) {
        
        return workHourDAO.findWorkHourById(theWorkHourId);
    }

    
    
}
