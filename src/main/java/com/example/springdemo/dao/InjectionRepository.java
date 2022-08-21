package com.example.springdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springdemo.entity.Injection;

public interface InjectionRepository extends JpaRepository<Injection, Integer> {
    
}
