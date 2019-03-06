package com.project.yellowRest.service;

import com.project.yellowRest.model.Report;
import com.project.yellowRest.repository.ReportJDBCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    private final ReportJDBCRepository reportJDBCRepository;

    @Autowired
    public ReportService(ReportJDBCRepository reportJDBCRepository) {
        this.reportJDBCRepository = reportJDBCRepository;
    }

    public List<Report> showReportsByUserId(Long userId){
        return reportJDBCRepository.findReportsByUserId(userId);
    }
}
