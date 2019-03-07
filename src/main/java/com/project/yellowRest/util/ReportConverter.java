package com.project.yellowRest.util;

import com.project.yellowRest.model.Report;
import com.project.yellowRest.projection.ReportProjection;

import java.util.ArrayList;
import java.util.List;

public class ReportConverter {

    private static Report convertToModel(ReportProjection reportProjection){
        return new Report(reportProjection.getUserId(),
                reportProjection.getYear(), reportProjection.getWeek(),
                reportProjection.getAvSpeed(), reportProjection.getAvTime(),
                reportProjection.getTotalDistance());
    }

    public static List<Report> convertToModel(List<ReportProjection> reportProjections){
        List<Report> reports = new ArrayList<>();
        for (ReportProjection reportProjection : reportProjections) {
            reports.add(convertToModel(reportProjection));
        }
        return reports;
    }
}
