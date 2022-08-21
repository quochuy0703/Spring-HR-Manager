package com.example.springdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springdemo.entity.Covid;

public interface CovidRepository extends JpaRepository<Covid, Integer> {
    
}
