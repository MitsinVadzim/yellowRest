package com.project.yellowRest.service;

import com.project.yellowRest.model.Report;
import com.project.yellowRest.repository.ReportJDBCRepository;
import com.project.yellowRest.util.ReportConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements com.project.yellowRest.service.interfaces.ReportService {

    private final ReportJDBCRepository reportJDBCRepository;

    @Autowired
    public ReportServiceImpl(ReportJDBCRepository reportJDBCRepository) {
        this.reportJDBCRepository = reportJDBCRepository;
    }

    @Override
    public List<Report> showReportsByUserId(Long userId){
        return ReportConverter.convertToModel(reportJDBCRepository.findReportsByUserId(userId));
    }
}
