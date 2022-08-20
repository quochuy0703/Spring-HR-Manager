package com.example.springdemo.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.springdemo.entity.AnnualLeave;

@Repository
public class AnnualLeaveDAOImpl implements AnnualLeaveDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void saveAnnualLeave(AnnualLeave annualLeave) {
        Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(annualLeave);
        
    }
    
}
