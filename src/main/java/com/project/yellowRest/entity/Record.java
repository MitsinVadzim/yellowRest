package com.project.yellowRest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Please enter distance")
    @Digits(integer = 10, fraction = 0, message = "The distance must be no more than 100 characters")
    private Integer distance;

    @NotNull(message = "Please enter race time")
    private Double time;

    @NotBlank(message = "Please enter race date")
    private String date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User author;

    @Column(insertable = false, updatable = false, name = "user_id")
    private Long userId;

    private String filename;

    public Record() {
    }

    public Record(int distance, double time, String date, User author) {
        this.distance = distance;
        this.time = time;
        this.date = date;
        this.author = author;
        this.userId = author.getId();
    }

}
