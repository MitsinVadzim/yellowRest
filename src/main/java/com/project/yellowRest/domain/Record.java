package com.project.yellowRest.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Record{
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
    //@JsonBackReference
    @JsonIgnore
    private User author;

//    @Column(insertable = false, updatable = false)
//    private Long userId;

    private String filename;

    public Record() {
    }

    public Record(int distance, double time, String date, User author) {
        this.distance = distance;
        this.time = time;
        this.date = date;
        this.author = author;
        //userId = user.getId();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User userId) {
        this.author = author;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
