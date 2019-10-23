package org.dima.counter.dao;

import org.dima.counter.entity.DailyReport;
import org.dima.counter.entity.WeeklyHoursList;
import org.dima.counter.entity.WeeklyPayment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Repository
public class CounterDaoImpl implements CounterDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void addWeeklyReport(DailyReport dailyReport) {
        Session session = sessionFactory.getCurrentSession();
        session.save(dailyReport);
    }

    @Override
    public void deleteReport() {

    }

    @Override
    public void updateReport() {

    }

    @Override
    @Transactional
    public WeeklyHoursList getWeeklyHoursListByDate(Date endingDate) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from DailyReport dr where dr.weekEndingDate = :endingDate"
                , DailyReport.class);
        query.setParameter("endingDate", endingDate);
        List<DailyReport> dailyReports = query.list();
        WeeklyHoursList weeklyHoursList = new WeeklyHoursList();
        weeklyHoursList.setDailyReportsList(dailyReports);
        return weeklyHoursList;
    }

    @Override
    @Transactional
    public List<Date> getPaySlipsList() {
        Session session = sessionFactory.getCurrentSession();
        List<Date> PaySlipsList = session.createQuery("select distinct weekEndingDate from DailyReport").list();
        return PaySlipsList;
    }

    @Override
    @Transactional
    public void addWeeklyWages(WeeklyPayment weeklyPayment) {
        Session session = sessionFactory.getCurrentSession();
        session.save(weeklyPayment);
    }

    @Override
    @Transactional
    public double getYearlyGrossEarnings(int userId, Date currentDate) {
        return 0;
    }

    @Override
    @Transactional
    public double getYearlyPaye(int userId, Date currentDate) {
        return 0;
    }
}