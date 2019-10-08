package org.dima.counter.service;

import org.dima.counter.dao.CounterDao;
import org.dima.counter.entity.DailyReport;
import org.dima.counter.entity.WeeklyHoursList;
import org.dima.counter.entity.WeeklyPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CounterServiceImpl implements CounterService {

    @Autowired
    private CounterDao counterDao;

    @Override
    public void addWeeklyReport(DailyReport dailyReport) {
        counterDao.addWeeklyReport(dailyReport);
    }

    @Override
    public void deleteReport() {

    }

    @Override
    public void updateReport() {

    }

    @Override
    public WeeklyHoursList getWeeklyHoursListByDate(String weekEndingDate) {
        return counterDao.getWeeklyHoursListByDate(weekEndingDate);
    }

    @Override
    public List<String> getAllWeeklyHoursLists() {
        return counterDao.getAllWeeklyHoursLists();
    }

    @Override
    public void addWeeklyWages(WeeklyPayment weeklyPayment) {
        counterDao.addWeeklyWages(weeklyPayment);
    }
}
