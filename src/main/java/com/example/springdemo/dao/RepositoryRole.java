package com.example.springdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springdemo.entity.Role;

public interface RepositoryRole extends JpaRepository<Role, Integer>{
    
}
