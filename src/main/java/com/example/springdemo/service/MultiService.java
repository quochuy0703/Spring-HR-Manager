package com.example.springdemo.service;

import java.util.List;

import com.example.springdemo.entity.AnnualLeave;
import com.example.springdemo.entity.Injection;
import com.example.springdemo.entity.Temp;
import com.example.springdemo.entity.User;
import com.example.springdemo.entity.WorkHour;

public interface MultiService {
    public User findUserById(int theId);
    public void saveWorkHour(WorkHour workHour) ;
    public void saveUser(User theUser);

    public void saveAnnualLeave(AnnualLeave annualLeave);

    public List<Temp> findTempByUserId(int userId);

    public void saveTemp(Temp theTemp);

    public void saveInjection(Injection injection);
}
