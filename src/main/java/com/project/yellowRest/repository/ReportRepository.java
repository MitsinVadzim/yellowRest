package com.project.yellowRest.repository;

import com.project.yellowRest.model.ReportModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;

public interface ReportRepository{

    RowMapper<ReportModel> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new ReportModel(resultSet.getLong("user_id"), resultSet.getInt("year"),
                resultSet.getInt("week"), resultSet.getDouble("avgspeed"),
                resultSet.getDouble("avtime"), resultSet.getInt("totaldistance"));
    };
}
