package com.aliction.firstnthirds.user.entities;

public class UserTeamRole{
    
    private Long id;
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