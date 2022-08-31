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

        Query<AnnualLeave> query = session.createQuery("from AnnualLeave where id = :theId", AnnualLeave.class);

        query.setParameter("theId", theId);

        List<AnnualLeave> theLeaves = query.list();

        return theLeaves;
        
    }

    @Override
    public List<AnnualLeave> findAnnualLeaveByIdAndByMonth(int theId, String month, String year) {
        Session session = entityManager.unwrap(Session.class);

        Query<AnnualLeave> query = session.createQuery("from AnnualLeave w where w.user.id = :theId and (date_format(w.startDate,'%Y-%m') =:word or date_format(w.endDate,'%Y-%m') =:word)", AnnualLeave.class);

        query.setParameter("theId", theId);
        query.setParameter("word", year+"-"+month);

        List<AnnualLeave> theLeaves = query.getResultList();

        return theLeaves;
    }

    @Override
    public List<AnnualLeave> findAnnualLeaveByUserId(int theUserId) {
        Session session = entityManager.unwrap(Session.class);

        Query<AnnualLeave> query = session.createQuery("from AnnualLeave w where w.user.id = :theId", AnnualLeave.class);

        query.setParameter("theId", theUserId);

        List<AnnualLeave> theLeaves = query.list();

        return theLeaves;
    }
    
}
