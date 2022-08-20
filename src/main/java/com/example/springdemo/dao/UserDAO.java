package com.example.springdemo.dao;

import com.example.springdemo.entity.User;

public interface UserDAO {
    public User findUserById(int theId);

    public void saveUser(User theUser);
    
}
