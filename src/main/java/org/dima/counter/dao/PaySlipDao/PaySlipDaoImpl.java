package org.dima.counter.dao.PaySlipDao;

import org.dima.counter.entity.payments.PaySlip;
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
    public void addPaySlip(PaySlip weeklyPaySlip) {
        Session session = sessionFactory.getCurrentSession();
        session.save(weeklyPaySlip);
    }

    @Override
    @Transactional
    public List<String> getPaySlipsList() {
        Session session = sessionFactory.getCurrentSession();
        return (List<String>) session.createQuery("select distinct weekEndingDate from WeeklyPaySlip").list();
    }

    @Override
    @Transactional
    public PaySlip getPaySlipByDate(String weekEndingDate) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from WeeklyPaySlip wp where wp.weekEndingDate = :endingDate"
                , PaySlip.class);
        query.setParameter("endingDate", weekEndingDate);
        return (PaySlip) query.uniqueResult();
    }

    @Override
    @Transactional
    public List<PaySlip> getAllWeeklyPayments() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from PaySlip", PaySlip.class).list();
    }

    @Override
    @Transactional
    public void updatePaySlip(PaySlip weeklyPayment) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("UPDATE WeeklyPaySlip wp set wp.totalHours = :totalHours, wp.grossEarnings = :grossEarnings, " +
                "wp.paye = :paye, wp.accAmount = :accAmount, wp.netPay = :netPay where wp.weekEndingDate = :endingDate");
        query.setParameter("totalHours", weeklyPayment.getTotalHours());
        query.setParameter("grossEarnings", weeklyPayment.getGrossEarnings());
        query.setParameter("paye", weeklyPayment.getPaye());
        query.setParameter("accAmount", weeklyPayment.getAccAmount());
        query.setParameter("netPay", weeklyPayment.getNetPay());
        query.setParameter("endingDate", weeklyPayment.getWeekEndingDate());
        query.executeUpdate();
    }

    @Override
    @Transactional
    public String deletePaySlip(String weekEndingDate) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from WeeklyPaySlip wp where wp.weekEndingDate = :endingDate");
        query.setParameter("endingDate", weekEndingDate);
        int result = query.executeUpdate();
        if (result == 0) {
            return "incorrectInput";
        } else {
            return "success";
        }
    }
}
