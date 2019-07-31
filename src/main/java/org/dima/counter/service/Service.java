package org.dima.counter.service;

import org.dima.counter.entity.WeeklyReport;

public interface Service {
    void addReport();

    void deleteReport();

    void updateReport();

    WeeklyReport getReportById(int id);
}
