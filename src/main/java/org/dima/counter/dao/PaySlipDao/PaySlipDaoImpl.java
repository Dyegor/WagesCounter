package org.dima.counter.dao.PaySlipDao;

import org.dima.counter.entity.payments.WeeklyPayment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class PaySlipDaoImpl implements PaySlipDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void addPaySlip(WeeklyPayment weeklyPayment) {
        Session session = sessionFactory.getCurrentSession();
        session.save(weeklyPayment);
    }

    @Override
    @Transactional
    public List<String> getPaySlipsList() {
        Session session = sessionFactory.getCurrentSession();
        return (List<String>) session.createQuery("select distinct weekEndingDate from WeeklyPayment").list();
    }

    @Override
    @Transactional
    public WeeklyPayment getPaySlipByDate(String weekEndingDate) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from WeeklyPayment wp where wp.weekEndingDate = :endingDate"
                , WeeklyPayment.class);
        query.setParameter("endingDate", weekEndingDate);
        return (WeeklyPayment) query.uniqueResult();
    }

    @Override
    @Transactional
    public List<WeeklyPayment> getAllWeeklyPayments() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from WeeklyPayment", WeeklyPayment.class).list();
    }

    @Override
    @Transactional
    public String deletePaySlip(String weekEndingDate) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from WeeklyPayment wp where wp.weekEndingDate = :endingDate");
        query.setParameter("endingDate", weekEndingDate);
        int result = query.executeUpdate();
        if (result == 0) {
            return "incorrectInput";
        } else {
            return "success";
        }
    }
}
