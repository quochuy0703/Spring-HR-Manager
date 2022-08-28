package com.example.springdemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
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

    @Override
    public User findUserByEmail(String email) {
        Session session = entityManager.unwrap(Session.class);

        Query<User> query = session.createQuery("from User where email=:email", User.class);

        query.setParameter("email", email);



        return query.getResultList().get(0);
    }

    @Override
    public List<User>  findEmployeeByDepartment(String department) {

        Session session = entityManager.unwrap(Session.class);

        Query<User> query = session.createQuery("from User where department=:department and manager = false", User.class);

        query.setParameter("department", department);
        
        return query.getResultList();
    }
    
}
