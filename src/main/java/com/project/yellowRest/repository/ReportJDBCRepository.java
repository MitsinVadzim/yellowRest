package com.project.yellowRest.repository;

import com.project.yellowRest.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class ReportJDBCRepository {

    private static final String SQL_FIND_ALL = "SELECT date_part('year', date::date) as year,\n" +
            "date_part('week', date::date) AS week,\n" +
            "\t   SUM(distance) as totaldistance,\n" +
            "\t   AVG(distance/time) as avspeed,\n" +
            "\t   AVG(time) as avtime,\n" +
            "       user_id as userid\n" +
            "FROM record\n" +
            "GROUP BY year, week, userid;";

    private static final String SQL_FIND_ALL_BY_ID = "SELECT date_part('year', date::date) as year,\n" +
            "date_part('week', date::date) AS week,\n" +
            "\t   SUM(distance) as totaldistance,\n" +
            "\t   AVG(distance/time) as avspeed,\n" +
            "\t   AVG(time) as avtime,\n" +
            "       user_id as userid\n" +
            "FROM record\n" +
            "WHERE user_id = ?"+
            "GROUP BY year, week, userid;";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ReportJDBCRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    RowMapper<Report> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> new Report(resultSet.getLong("userid"), resultSet.getDouble("year"),
            resultSet.getDouble("week"), resultSet.getDouble("avspeed"),
            resultSet.getDouble("avtime"), resultSet.getLong("totaldistance"));


    public List<Report> findReportsByUserId(Long userId){

        return this.jdbcTemplate.query(SQL_FIND_ALL_BY_ID, new Object[]{userId},
                ROW_MAPPER);
    }

}
