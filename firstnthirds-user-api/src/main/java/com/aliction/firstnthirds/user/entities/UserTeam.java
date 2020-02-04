package com.aliction.firstnthirds.user.entities;

public class UserTeam{

    private Long id;
    private Long user;
    private Team team;
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