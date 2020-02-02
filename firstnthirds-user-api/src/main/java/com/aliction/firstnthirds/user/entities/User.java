package com.aliction.firstnthirds.user.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;


@Entity
@NamedQuery(name = "User.findAll",
      query = "SELECT user FROM User user ORDER BY user.firstName",
hints = @QueryHint(name = "org.hibernate.cacheable", value = "true") )
public class User{
    

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  @Column(nullable=false)
  private String firstName;
  @Column(nullable=false)
  private String lastName;
  @Column(nullable=false)
  private String email;
  @Column(nullable=false)
  private String city;
  private String state;
  @Column(nullable=false)
  private String country;
  private Long groupId;

    public User(){
    }

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

  


}