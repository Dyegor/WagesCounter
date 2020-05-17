package org.dima.counter.dao.TimeSheetDao;

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
public class TimeSheetDaoImpl implements TimeSheetDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void addTimeSheet(DailyReport dailyReport) {
        Session session = sessionFactory.getCurrentSession();
        session.save(dailyReport);
    }

    @Override
    @Transactional
    public WeeklyHoursList getTimeSheetByDate(String weekEndingDate) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from DailyReport dr where dr.weekEndingDate = :endingDate"
                , DailyReport.class);
        query.setParameter("endingDate", weekEndingDate);
        List<DailyReport> dailyReports = query.list();
        WeeklyHoursList weeklyHoursList = new WeeklyHoursList();
        weeklyHoursList.setDailyReportsList(dailyReports);
        return weeklyHoursList;
    }

    @Override
    @Transactional
    public String deleteTimeSheet(String weekEndingDate) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from DailyReport dr where dr.weekEndingDate = :endingDate");
        query.setParameter("endingDate", weekEndingDate);
        int result = query.executeUpdate();
        if (result == 0) {
            return "incorrectInput";
        } else {
            return "success";
        }
    }
}
