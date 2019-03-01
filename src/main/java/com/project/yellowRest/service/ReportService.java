package com.project.yellowRest.service;

import com.project.yellowRest.entity.Record;
import com.project.yellowRest.entity.Report;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {

    private List<Report> reports;

    public List<Report> getReports(List<Record> records) {
        reports = new ArrayList<>();
        Record record = records.get(0);
        reports.add(startInit(record));

        for(int i = 1; i < records.size(); i++){
            addRecord(records.get(i));
        }
        return reports;
    }

    private String getLastDayOfTheWeek(Report report){
        int firstDay = Integer.parseInt(report.getFirstDayOfTheWeek().substring(8));
        int firstMonth = Integer.parseInt(report.getFirstDayOfTheWeek().substring(5, 7));
        int firstYear = Integer.parseInt(report.getFirstDayOfTheWeek().substring(0, 4));
        firstDay += 6;
        if (firstDay > 30){
            firstDay-=30;
            ++firstMonth;
            if (firstMonth > 12){
                firstMonth-=12;
                ++firstYear;
            }
        }
        return firstYear+"-"+firstMonth+"-"+firstDay;
    }

    private void addRecord(Record record){
        if(record.getDate().compareTo(reports.get(reports.size()-1).getLastDayOfTheWeek()) > 0){
            reports.add(startInit(record));
        }else{
            updateReport(reports.get(reports.size()-1), record);
        }
    }

    private void updateReport(Report report, Record record){
        report.setTotalDistance(report.getTotalDistance() + record.getDistance());
        report.setAvTime((report.getAvTime()+record.getTime()) /2);
        report.setAvSpeed((report.getAvSpeed()+ ((double)record.getDistance()/record.getTime())) /2);
    }

    private Report startInit(Record record) {
        if(record == null){
            return null;
        }
        Report report = new Report(record.getDate(), record.getTime(), record.getDistance());
        report.setLastDayOfTheWeek(getLastDayOfTheWeek(report));
        report.setAvSpeed((double)report.getTotalDistance()/report.getAvTime());
        return report;
    }

}
