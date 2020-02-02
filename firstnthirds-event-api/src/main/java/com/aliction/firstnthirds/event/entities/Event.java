package com.aliction.firstnthirds.event.entities;

import java.util.Date;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.QueryHint;

@Entity
@NamedQuery(name = "Event.findAll", query = "SELECT event FROM Event event ORDER BY event.name", hints = @QueryHint(name = "org.hibernate.cacheable", value = "true"))
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    @JsonbDateFormat(value = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Integer duration;
    
    @Column(nullable = false)
    private String location;
    
    @Column(nullable = false)
    private String description;
    
    @Column(nullable = false)
    private String pictures_url;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "status", referencedColumnName = "id",  nullable = false)
    private EventStatus status;

    @Column(nullable = false)
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