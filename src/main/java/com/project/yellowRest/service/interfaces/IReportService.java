package com.project.yellowRest.service.interfaces;

import com.project.yellowRest.model.Report;

import java.util.List;

public interface IReportService {
    List<Report> showReportsByUserId(Long userId);
}
