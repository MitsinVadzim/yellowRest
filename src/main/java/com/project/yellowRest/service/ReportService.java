package com.project.yellowRest.service;

import com.project.yellowRest.model.Report;
import com.project.yellowRest.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ReportService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Report> showReports(){
        String sql = "SELECT date_part('year', date::date) as year,\n" +
                "date_part('week', date::date) AS week,\n" +
                "\t   SUM(distance) as totaldistance,\n" +
                "\t   AVG(distance/time) as avspeed,\n" +
                "\t   AVG(time) as avtime,\n" +
                "       user_id as userid\n" +
                "FROM record\n" +
                "GROUP BY year, week, userid;";
        return this.jdbcTemplate.query(sql,
                ReportRepository.ROW_MAPPER);
    }

    public List<Report> showReportsByUserId(Long userId){
        String sql = "SELECT date_part('year', date::date) as year,\n" +
                "date_part('week', date::date) AS week,\n" +
                "\t   SUM(distance) as totaldistance,\n" +
                "\t   AVG(distance/time) as avspeed,\n" +
                "\t   AVG(time) as avtime,\n" +
                "       user_id as userid\n" +
                "FROM record\n" +
                "WHERE user_id = ?"+
                "GROUP BY year, week, userid;";
        return this.jdbcTemplate.query(sql, new Object[]{userId},
                ReportRepository.ROW_MAPPER);
    }
}
