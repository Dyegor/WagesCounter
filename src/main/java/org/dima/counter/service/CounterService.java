package org.dima.counter.service;

import org.dima.counter.entity.WeeklyReport;

public interface Service {
    WeeklyReport addReport();

    void deleteReport();

    void updateReport();

    WeeklyReport getReportById(int id);
}
