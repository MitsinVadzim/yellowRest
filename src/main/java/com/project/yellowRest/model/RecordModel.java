package com.project.yellowRest.model;

import lombok.Data;

import javax.validation.constraints.Null;

@Data
public class RecordModel {

    @Null
    private Long id;

    private Integer distance;

    private Double time;

    private String date;

    private Long userId;

    private String filename;

}
