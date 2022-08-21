package com.example.springdemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.springdemo.entity.WorkHour;

@Repository
public class WorkHourDAOImpl implements WorkHourDAO {

    @Autowired
    private EntityManager entityManager;

	@Override
	public List<WorkHour> findWorkHourByUserId(int theUserId) {
        
		

        Query query = entityManager.createQuery("from WorkHour w where w.user_id = :userId", WorkHour.class);

        query.setParameter("userId", theUserId);

        List<WorkHour> theWorkHours = query.getResultList();

		return theWorkHours;
	}

    @Override
    public void saveWorkHour(WorkHour workHour) {

        
        
        entityManager.merge(workHour);
        
    }
    
}
