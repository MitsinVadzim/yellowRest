package com.project.yellowRest.controller;

import com.project.yellowRest.domain.Report;
import com.project.yellowRest.domain.User;
import com.project.yellowRest.repository.UserRepository;
import com.project.yellowRest.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ReportController {

    private final UserRepository userRepository;
    private final ReportService reportService;

    @Autowired
    public ReportController(UserRepository userRepository, ReportService reportService) {
        this.userRepository = userRepository;
        this.reportService = reportService;
    }

    @GetMapping("/reports")
    public List<Report> userRecords(
            @AuthenticationPrincipal User user
    ) {
        return reportService.getReports(user.getRecords());
    }
}