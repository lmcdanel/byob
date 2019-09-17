package com.example.byobproject.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {



    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3,max=20)
    private String username;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

    @ManyToMany
    private List<Beer> beers;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Beer> getBeers() {
        return beers;
    }

    public void addItem(Beer item) {
        beers.add(item);
    }
}
