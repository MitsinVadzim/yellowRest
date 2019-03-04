package com.project.yellowRest.service;

import com.project.yellowRest.model.ReportModel;
import com.project.yellowRest.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ReportService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ReportModel> showReports(){
        return this.jdbcTemplate.query("SELECT date_part('year', date::date) as year,\n" +
                "       date_part('week', date::date) AS week,\n" +
                "\t   SUM(distance) as totaldistance,\n" +
                "\t   AVG(distance/time) as avspeed,\n" +
                "\t   AVG(time) as avtime" +
                "       user_id\n" +
                "FROM record\n" +
                "GROUP BY year, week, user_id;",
                ReportRepository.ROW_MAPPER);
//        List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT date_part('year', date::date) as year,\n" +
//                "       date_part('week', date::date) AS week,\n" +
//                "\t   SUM(distance) as totaldistance,\n" +
//                "\t   AVG(distance/time) as avspeed,\n" +
//                "\t   AVG(time) as avtime" +
//                "       user_id\n" +
//                "FROM record\n" +
//                "GROUP BY year, week, user_id;");
//        List<ReportModel> reportModels = new ArrayList<>();
//        for (Map row : rows) {
//            ReportModel reportModel = new ReportModel(row.get("user_id"), row.get("year"),
//                    row.get("week"), row.get("avgspeed"),
//                    row.get("avtime"), row.get("totaldistance"));
//            reportModels.add(reportModel);
//        }
    }
}
