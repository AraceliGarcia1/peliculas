package com.example.model.movie;


import java.sql.Date;


public class BeanMovie {
    int id;
    String name;
    String description;
    Date releaseDate;
    int takings;
    int status;

    public BeanMovie(int id, String name, String description, Date releaseDate, int takings, int status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.takings = takings;
        this.status = status;
    }

    public BeanMovie() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getTakings() {
        return takings;
    }

    public void setTakings(int takings) {
        this.takings = takings;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }




}