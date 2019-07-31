package org.dima.counter.dao;

import org.dima.counter.entity.WeeklyReport;

public interface Dao {
    void addReport();

    void deleteReport();

    void updateReport();

    WeeklyReport getReportById(int id);
}