package com.example.springdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springdemo.dao.UserDAO;
import com.example.springdemo.dao.WorkHourDAO;
import com.example.springdemo.entity.User;
import com.example.springdemo.entity.WorkHour;

@Service
public class MultiServiceImpl implements MultiService{

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private WorkHourDAO workHourDAO;

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
    
}
