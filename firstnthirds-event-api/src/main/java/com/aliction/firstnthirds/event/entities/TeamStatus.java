package com.aliction.firstnthirds.event.entities;

public class TeamStatus{
    
  private Long id;
  private String provisionStatus;

    public TeamStatus(){

    }

    public TeamStatus(String provisionStatus) {
        this.provisionStatus = provisionStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProvisionStatus() {
        return provisionStatus;
    }

    public void setProvisionStatus(String provisionStatus) {
        this.provisionStatus = provisionStatus;
    }
}
