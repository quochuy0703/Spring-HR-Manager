package com.example.springdemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.springdemo.entity.WorkHour;

@Repository
public class WorkHourDAOImpl implements WorkHourDAO {

    @Autowired
    private EntityManager entityManager;

	@Override
	public List<WorkHour> findWorkHourByUserId(int theUserId) {
        
		Session session = entityManager.unwrap(Session.class);

        Query query = session.createQuery("from WorkHour w where w.user_id = :userId", WorkHour.class);

        query.setParameter("userId", theUserId);

        List<WorkHour> theWorkHours = query.list();

		return theWorkHours;
	}

    @Override
    public void saveWorkHour(WorkHour workHour) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(workHour);
        
    }
    
}
