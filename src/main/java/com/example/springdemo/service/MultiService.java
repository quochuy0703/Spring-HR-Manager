package com.example.springdemo.service;

import com.example.springdemo.entity.User;
import com.example.springdemo.entity.WorkHour;

public interface MultiService {
    public User findUserById(int theId);
    public void saveWorkHour(WorkHour workHour) ;
    public void saveUser(User theUser);
}
