package com.project.yellowRest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "record")
public class RecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Please enter distance")
    @Digits(integer = 10, fraction = 0, message = "The distance must be no more than 100 characters")
    private Integer distance;

    @NotNull(message = "Please enter race time")
    private Double time;

    @NotBlank(message = "Please enter race date")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserEntity author;

    @Column(insertable = false, updatable = false, name = "user_id")
    private Long userId;

    private String filename;

    public RecordEntity() {
    }

    public RecordEntity(int distance, double time, LocalDate date, UserEntity author) {
        this.distance = distance;
        this.time = time;
        this.date = date;
        this.author = author;
        this.userId = author.getId();
    }

}