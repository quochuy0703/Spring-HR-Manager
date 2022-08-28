package com.example.springdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.springdemo.entity.UserLogin;

public interface RepositoryUserLogin extends JpaRepository<UserLogin, Integer> {
    @Query("Select u from UserLogin u where u.email =?1")
    public UserLogin findUserByEmail(String email);
}
