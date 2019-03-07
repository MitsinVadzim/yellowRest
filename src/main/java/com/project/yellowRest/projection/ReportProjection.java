package com.project.yellowRest.projection;

import lombok.Data;

@Data
public class ReportProjection {
    private int week;
    private int year;
    private Double avSpeed = 0D;
    private Double avTime;
    private Long totalDistance;
    private Long userId;


    public ReportProjection(){}

    public ReportProjection(Long userId, Double year, Double week, Double avSpeed, Double avTime, Long totalDistance) {
        this.userId = userId;
        this.year = year.intValue();
        this.week = week.intValue();
        this.avSpeed = avSpeed;
        this.avTime = avTime;
        this.totalDistance = totalDistance;
    }
}
