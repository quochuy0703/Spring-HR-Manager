package com.example.springdemo.dao;

import java.util.List;

import com.example.springdemo.entity.Temp;

public interface TempDAO {
    public List<Temp> findTempByUserId(int userId);
    public void saveTemp(Temp theTemp);
}
