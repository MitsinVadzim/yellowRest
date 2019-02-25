package com.project.yellowRest.domain;

public class Report {
    private String firstDayOfTheWeek;
    private String lastDayOfTheWeek;
    private Double avSpeed = 0D;
    private Double avTime;
    private Integer totalDistance;

    public Report(){}

    public Report(String firstDayOfTheWeek, Double avTime, Integer totalDistance) {
        this.firstDayOfTheWeek = firstDayOfTheWeek;
        this.avTime = avTime;
        this.totalDistance = totalDistance;
    }

    public String getFirstDayOfTheWeek() {
        return firstDayOfTheWeek;
    }

    public void setFirstDayOfTheWeek(String firstDayOfTheWeek) {
        this.firstDayOfTheWeek = firstDayOfTheWeek;
    }

    public String getLastDayOfTheWeek() {
        return lastDayOfTheWeek;
    }

    public void setLastDayOfTheWeek(String lastDayOfTheWeek) {
        this.lastDayOfTheWeek = lastDayOfTheWeek;
    }

    public Double getAvSpeed() {
        return avSpeed;
    }

    public void setAvSpeed(Double avSpeed) {
        this.avSpeed = avSpeed;
    }

    public Double getAvTime() {
        return avTime;
    }

    public void setAvTime(Double avTime) {
        this.avTime = avTime;
    }

    public Integer getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(Integer totalDistance) {
        this.totalDistance = totalDistance;
    }
}
