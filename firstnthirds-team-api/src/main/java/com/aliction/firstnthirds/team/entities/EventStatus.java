package com.aliction.firstnthirds.team.entities;

public class EventStatus{
    
    private Long id;
    private String status;

    public EventStatus(){

    }

    public EventStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}