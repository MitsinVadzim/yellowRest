package com.project.yellowRest.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Report {
    private LocalDate firstDayOfTheWeek;
    private LocalDate lastDayOfTheWeek;
    private Double avSpeed = 0D;
    private Double avTime;
    private Integer totalDistance;

    public Report(){}

    public Report(LocalDate firstDayOfTheWeek, Double avTime, Integer totalDistance) {
        this.firstDayOfTheWeek = firstDayOfTheWeek;
        this.avTime = avTime;
        this.totalDistance = totalDistance;
    }
}
