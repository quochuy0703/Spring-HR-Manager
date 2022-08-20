package com.example.springdemo.service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springdemo.dao.AnnualLeaveDAO;
import com.example.springdemo.dao.UserDAO;
import com.example.springdemo.dao.WorkHourDAO;
import com.example.springdemo.entity.AnnualLeave;
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
    public void saveAnnualLeave(AnnualLeave annualLeave) {
        
        annualLeaveDAO.saveAnnualLeave(annualLeave);
        
    }
    
}
