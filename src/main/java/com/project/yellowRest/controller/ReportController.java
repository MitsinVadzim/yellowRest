package com.project.yellowRest.controller;

import com.project.yellowRest.model.Report;
import com.project.yellowRest.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/reports")
    public List<Report> showReports() {
        return reportService.showReports();
    }
}
