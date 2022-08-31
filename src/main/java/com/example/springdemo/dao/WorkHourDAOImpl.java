package com.example.springdemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;



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

    @Override
    public List<WorkHour> findWorkHourByUserIdAndByMonth(int theUserId, String month, String year) {
        Query query = entityManager.createQuery("from WorkHour w where w.user.id = :userId and date_format(w.startHour,'%Y-%m') =:word", WorkHour.class);

        query.setParameter("userId", theUserId);
        query.setParameter("word", year+"-"+month);

        List<WorkHour> theWorkHours = query.getResultList();

		return theWorkHours;
    }

    @Override
    public void deleteWorkHourById(int theWorkHourId) {
        
        WorkHour workHour = entityManager.find(WorkHour.class, theWorkHourId);
        if(workHour == null) return ;
        entityManager.remove(workHour);
    }

    @Override
    public WorkHour findWorkHourById(int theWorkHourId) {
        WorkHour workHour = entityManager.find(WorkHour.class, theWorkHourId);
        return workHour;
    }
    
}
