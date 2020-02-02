package com.aliction.firstnthirds.event.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;


@Entity
@NamedQuery(name = "EventStatus.findAll",
      query = "SELECT eventStatus FROM EventStatus eventStatus ORDER BY eventStatus.status",
hints = @QueryHint(name = "org.hibernate.cacheable", value = "true") )
public class EventStatus{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
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