package com.aliction.firstnthirds.user.entities;

import java.util.Date;

import javax.json.bind.annotation.JsonbDateFormat;

public class Event {

    private Long id;
    private String name;
    private String type;
    @JsonbDateFormat(value = "yyyy-MM-dd HH:mm:ss")
    private Date date;
    private Integer duration;
    private String location;
    private String description;
    private String pictures_url;
    private EventStatus status;
    private Long teamId;

    public Event(){

    }

    public Event(String name, String type, Date date, Integer duration, String location, String description,
            String pictures_url, EventStatus status, Long groupId) {
        this.name = name;
        this.type = type;
        this.date = date;
        this.duration = duration;
        this.location = location;
        this.description = description;
        this.pictures_url = pictures_url;
        this.status = status;
        this.teamId = groupId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictures_url() {
        return pictures_url;
    }

    public void setPictures_url(String pictures_url) {
        this.pictures_url = pictures_url;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long groupId) {
        this.teamId = groupId;
    }


}