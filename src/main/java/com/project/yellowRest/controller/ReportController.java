package com.project.yellowRest.controller;

import com.project.yellowRest.entity.Report;
import com.project.yellowRest.entity.User;
import com.project.yellowRest.model.ReportModel;
import com.project.yellowRest.repository.RecordRepository;
import com.project.yellowRest.repository.UserRepository;
import com.project.yellowRest.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/reports")
    public List<ReportModel> showAll() {
        List<ReportModel> reportModels = reportService.showReports();
        return reportModels;
    }
}
