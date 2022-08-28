package com.example.springdemo.dao;

import java.util.List;

import com.example.springdemo.entity.User;

public interface UserDAO {
    public User findUserById(int theId);

    public void saveUser(User theUser);

    public User findUserByEmail(String email);

    public List<User> findEmployeeByDepartment(String department);
    
}
