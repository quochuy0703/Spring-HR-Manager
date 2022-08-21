package com.example.springdemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.springdemo.entity.Temp;

@Repository
public class TempDAOImpl implements TempDAO{

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Temp> findTempByUserId(int userId) {
        
        Session session = entityManager.unwrap(Session.class);

        Query query = session.createQuery("from Temp t where t.user_id = :userId");

        query.setParameter("userId", userId);
        List<Temp> theTemps = query.list();
        return theTemps;
    }

    @Override
    public void saveTemp(Temp theTemp) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(theTemp);
        
    }
    
}
