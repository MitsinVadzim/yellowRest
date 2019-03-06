package com.project.yellowRest.model;

import lombok.Data;
import org.apache.tomcat.jni.Local;

import javax.validation.constraints.Null;
import java.time.LocalDate;

@Data
public class Record {

    @Null
    private Long id;

    private Integer distance;

    private Double time;

    private LocalDate date;

    private Long userId;

    private String filename;

    public Record(Long id, Integer distance, Double time, LocalDate date, Long userId, String filename){
        this.id = id;
        this.distance = distance;
        this.time = time;
        this.date = date;
        this.userId = userId;
        this.filename = filename;
    }

    public Record(){}
}
