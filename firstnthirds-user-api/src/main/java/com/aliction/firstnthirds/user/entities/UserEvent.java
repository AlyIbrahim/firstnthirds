package com.aliction.firstnthirds.user.entities;

import javax.enterprise.inject.Default;
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
@NamedQuery(name="UserEvent.findAll", query = "SELECT userevent FROM UserEvent userevent",
hints = @QueryHint(name = "org.hibernate.cacheable", value = "true") )
public class UserEvent{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade= CascadeType.MERGE)
    @JoinColumn(name = "user", referencedColumnName= "id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Long event;

    @OneToOne(fetch = FetchType.EAGER, cascade= CascadeType.MERGE)
    @JoinColumn(name = "role", referencedColumnName= "id", nullable = false)
    public UserEventRole role;

    @OneToOne(fetch = FetchType.EAGER, cascade= CascadeType.MERGE)
    @JoinColumn(name = "status", referencedColumnName= "id")
    public UserEventStatus status;

    public UserEvent(){

    }

    public UserEvent(User user, Long event, UserEventRole role, UserEventStatus status) {
        this.user = user;
        this.event = event;
        this.role = role;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getEvent() {
        return event;
    }

    public void setEvent(Long event) {
        this.event = event;
    }

    public UserEventRole getRole() {
        return role;
    }

    public void setRole(UserEventRole role) {
        this.role = role;
    }

    public UserEventStatus getStatus() {
        return status;
    }

    public void setStatus(UserEventStatus status) {
        this.status = status;
    }

}