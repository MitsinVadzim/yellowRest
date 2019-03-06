package com.project.yellowRest.controller;

import com.project.yellowRest.model.Report;
import com.project.yellowRest.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserReportsController {

    private final ReportService reportService;

    @Autowired
    public UserReportsController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/users/{userid}/reports")
    public List<Report> showUserReports(@PathVariable("userid") Long userId){
        return reportService.showReportsByUserId(userId);
    }
}
