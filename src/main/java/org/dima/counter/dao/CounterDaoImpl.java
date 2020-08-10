package org.dima.counter.dao;

import org.dima.counter.entity.DailyReport;
import org.dima.counter.entity.WeeklyTimeSheet;
import org.dima.counter.entity.PaySlip;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CounterDaoImpl implements CounterDao {

    @Autowired
    private EntityManager entityManager;


    @Override
    @Transactional
    public void addTimeSheet(DailyReport dailyReport) {
        Session session = entityManager.unwrap(Session.class);
        session.save(dailyReport);
    }

    @Override
    @Transactional
    public WeeklyTimeSheet getTimeSheetByDate(String weekEndingDate) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from DailyReport dr where dr.weekEndingDate = :endingDate"
                , DailyReport.class);
        query.setParameter("endingDate", weekEndingDate);
        List<DailyReport> dailyReports = query.list();
        WeeklyTimeSheet weeklyTimeSheet = new WeeklyTimeSheet();
        weeklyTimeSheet.setDailyReportsList(dailyReports);
        return weeklyTimeSheet;
    }

    @Override
    @Transactional
    public void updateTimeSheet(DailyReport dailyReport) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("UPDATE DailyReport dr set dr.startTime = :startTime, dr.finishTime = :finishTime, " +
                "dr.hoursDone = :hoursDone where dr.weekEndingDate = :endingDate and dr.day = :day");
        query.setParameter("startTime", dailyReport.getStartTime());
        query.setParameter("finishTime", dailyReport.getFinishTime());
        query.setParameter("hoursDone", dailyReport.getHoursDone());
        query.setParameter("endingDate", dailyReport.getWeekEndingDate());
        query.setParameter("day", dailyReport.getDay());
        query.executeUpdate();
    }

    @Override
    @Transactional
    public String deleteTimeSheet(String weekEndingDate) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("delete from DailyReport dr where dr.weekEndingDate = :endingDate");
        query.setParameter("endingDate", weekEndingDate);
        int result = query.executeUpdate();
        if (result == 0) {
            return "incorrectInput";
        } else {
            return "success";
        }
    }

    @Override
    @Transactional
    public void addPaySlip(PaySlip weeklyPaySlip) {
        Session session = entityManager.unwrap(Session.class);
        session.save(weeklyPaySlip);
    }

    @Override
    @Transactional
    public List<String> getPaySlipsList() {
        Session session = entityManager.unwrap(Session.class);
        return (List<String>) session.createQuery("select distinct weekEndingDate from PaySlip").list();
    }

    @Override
    @Transactional
    public PaySlip getPaySlipByDate(String weekEndingDate) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from PaySlip wp where wp.weekEndingDate = :endingDate", PaySlip.class);
        query.setParameter("endingDate", weekEndingDate);
        return (PaySlip) query.uniqueResult();
    }

    @Override
    @Transactional
    public List<PaySlip> getAllWeeklyPayments() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("from PaySlip", PaySlip.class).list();
    }

    @Override
    @Transactional
    public void updatePaySlip(PaySlip weeklyPayment) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("UPDATE PaySlip wp set wp.normalHours = :normalHours, wp.overTimeHours = " +
                ":overTimeHours, wp.grossEarnings = :grossEarnings, wp.paye = :paye, wp.accAmount = :accAmount" +
                ", wp.netPay = :netPay where wp.weekEndingDate = :endingDate");
        query.setParameter("normalHours", weeklyPayment.getNormalHours());
        query.setParameter("overTimeHours", weeklyPayment.getOverTimeHours());
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
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("delete from PaySlip wp where wp.weekEndingDate = :endingDate");
        query.setParameter("endingDate", weekEndingDate);
        int result = query.executeUpdate();
        if (result == 0) {
            return "incorrectInput";
        } else {
            return "success";
        }
    }
}
