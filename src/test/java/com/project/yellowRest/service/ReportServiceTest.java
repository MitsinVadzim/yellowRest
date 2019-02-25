package com.project.yellowRest.service;

import com.project.yellowRest.domain.Record;
import com.project.yellowRest.domain.Report;
import com.project.yellowRest.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportServiceTest {
    @Autowired
    private ReportService reportService;

    @MockBean
    private User user;

    @Test
    public void startInit() {
        Record record = new Record(100, 100, "2018-10-20", user);
        List<Record> records = new ArrayList<>();
        records.add(record);
        List<Report> reports = reportService.getReports(records);
        Assert.assertNotNull(reports);
    }
}