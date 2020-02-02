package com.aliction.firstnthirds.team.entities;

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
@NamedQuery(name = "Team.findAll",
      query = "SELECT team FROM Team team ORDER BY team.name",
hints = @QueryHint(name = "org.hibernate.cacheable", value = "true") )
public class Team{
    

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable=false)
  private String name;
  @Column(nullable=false)
  private String city;
  private String state;
  @Column(nullable=false)
  private String country;
  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
  @JoinColumn(name = "status", referencedColumnName = "id", nullable = false)
  private TeamStatus status;

    public Team(){
    }

    public Team(String name, String city, String state, String country, TeamStatus status) {
        this.name = name;
        this.city = city;
        this.state = state;
        this.country = country;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public TeamStatus getStatus() {
        return status;
    }

    public void setStatus(TeamStatus status) {
        this.status = status;
    }

}