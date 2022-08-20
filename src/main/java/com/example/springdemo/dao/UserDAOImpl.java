package com.example.springdemo.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.springdemo.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public User findUserById(int theId) {
        
        Session session = entityManager.unwrap(Session.class);

        User theUser = session.get(User.class, theId);
        
        return theUser;
    }

    @Override
    public void saveUser(User theUser) {
        Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(theUser);
        
    }
    
}
