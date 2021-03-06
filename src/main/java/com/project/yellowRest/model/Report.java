package com.project.yellowRest.model;

import lombok.Data;

@Data
public class Report {
    private int week;
    private int year;
    private Double avSpeed = 0D;
    private Double avTime;
    private Long totalDistance;
    private Long userId;


    public Report(){}

    public Report(Long userId, int year, int week, Double avSpeed, Double avTime, Long totalDistance) {
        this.userId = userId;
        this.year = year;
        this.week = week;
        this.avSpeed = avSpeed;
        this.avTime = avTime;
        this.totalDistance = totalDistance;
    }
}
