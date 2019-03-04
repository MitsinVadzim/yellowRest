package com.project.yellowRest.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReportModel {
    private int week;
    private int year;
    private Double avSpeed = 0D;
    private Double avTime;
    private Integer totalDistance;
    private Long userId;


    public ReportModel(){}

    public ReportModel(Long userId, int year, int week, Double avSpeed, Double avTime, Integer totalDistance) {

    }
}
