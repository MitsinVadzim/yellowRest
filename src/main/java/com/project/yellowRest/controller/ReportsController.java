package com.project.yellowRest.controller;

import com.project.yellowRest.model.Report;
import com.project.yellowRest.service.ReportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReportsController {

    private final ReportServiceImpl reportServiceImpl;

    @Autowired
    public ReportsController(ReportServiceImpl reportServiceImpl) {
        this.reportServiceImpl = reportServiceImpl;
    }

    @GetMapping("/users/{userid}/reports")
    public List<Report> showUserReports(@PathVariable("userid") Long userId){
        return reportServiceImpl.showReportsByUserId(userId);
    }
}
