package com.project.yellowRest.model;

import lombok.Data;

import javax.validation.constraints.Null;
import java.time.LocalDateTime;

@Data
public class Record {

    @Null
    private Long id;

    private Integer distance;

    private Double time;

    private LocalDateTime date;

    private Long userId;


    public Record(Long id, Integer distance, Double time, LocalDateTime date, Long userId){
        this.id = id;
        this.distance = distance;
        this.time = time;
        this.date = date;
        this.userId = userId;
    }

    public Record(){}
}
