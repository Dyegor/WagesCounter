package org.dima.counter.dao;

import org.dima.counter.entity.DailyReport;
import org.dima.counter.entity.WeeklyHoursList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
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
    public WeeklyHoursList getWeeklyHoursListByDate(String endingDate) {
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
    public List<String> getAllWeeklyHoursLists() {
        Session session = sessionFactory.getCurrentSession();
        List<String> WeeklyHoursLists = session.createQuery("select distinct weekEndingDate from DailyReport").list();
        return WeeklyHoursLists;
    }
}