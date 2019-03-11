package com.project.yellowRest.service.interfaces;

import com.project.yellowRest.model.Report;

import java.util.List;

public interface ReportService {
    List<Report> showReportsByUserId(Long userId);
}
