package com.project.yellowRest.repository;

import com.project.yellowRest.model.ReportModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;

public interface ReportRepository{

    RowMapper<ReportModel> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> new ReportModel(resultSet.getLong("userid"), resultSet.getDouble("year"),
            resultSet.getDouble("week"), resultSet.getDouble("avspeed"),
            resultSet.getDouble("avtime"), resultSet.getLong("totaldistance"));
}
