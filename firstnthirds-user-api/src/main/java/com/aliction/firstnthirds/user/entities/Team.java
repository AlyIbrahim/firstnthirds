package com.aliction.firstnthirds.user.entities;


public class Team{
    
    
  private Long id;
  private String name;
  private String city;
  private String state;
  private String country;
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