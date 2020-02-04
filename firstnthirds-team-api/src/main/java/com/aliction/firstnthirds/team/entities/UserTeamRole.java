package com.aliction.firstnthirds.team.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;

@Entity
@NamedQuery(name = "UserTeamRole.findAll",
      query = "SELECT userteamrole FROM UserTeamRole userteamrole ORDER BY userteamrole.id",
hints = @QueryHint(name = "org.hibernate.cacheable", value = "true") )
public class UserTeamRole{
    // Check implumenting composite unique user+team

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String role;

    public UserTeamRole(){

    }

    public UserTeamRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}