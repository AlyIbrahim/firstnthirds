package com.aliction.firstnthirds.team.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;


@Entity
@NamedQuery(name = "TeamStatus.findAll",
      query = "SELECT teamStatus FROM TeamStatus teamStatus ORDER BY teamStatus.provisionStatus",
hints = @QueryHint(name = "org.hibernate.cacheable", value = "true") )
public class TeamStatus{
    

  @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

 @Column(nullable = false)
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
