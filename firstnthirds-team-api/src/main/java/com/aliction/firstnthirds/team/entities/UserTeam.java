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
@NamedQuery(name = "UserTeam.findAll",
      query = "SELECT userteam FROM UserTeam userteam ORDER BY userteam.id",
hints = @QueryHint(name = "org.hibernate.cacheable", value = "true") )
public class UserTeam{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long user;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "team", referencedColumnName = "id")
    private Team team;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "role", referencedColumnName = "id")
    private UserTeamRole role;

    public UserTeam() {
    }

    public UserTeam(Long user, Team team, UserTeamRole role) {
        this.user = user;
        this.team = team;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public UserTeamRole getRole() {
        return role;
    }

    public void setRole(UserTeamRole role) {
        this.role = role;
    }

}