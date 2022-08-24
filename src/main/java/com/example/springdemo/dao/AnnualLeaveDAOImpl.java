package com.example.springdemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
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

    @Override
    public List<AnnualLeave> findAnnualLeaveById(int theId) {
        
        Session session = entityManager.unwrap(Session.class);

        Query<AnnualLeave> query = session.createQuery("form AnnualLeave where id = :theId", AnnualLeave.class);

        List<AnnualLeave> theLeaves = query.list();

        return theLeaves;
        
    }
    
}
