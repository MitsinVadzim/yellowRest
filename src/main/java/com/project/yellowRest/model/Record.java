package com.project.yellowRest.model;

import lombok.Data;

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

}
