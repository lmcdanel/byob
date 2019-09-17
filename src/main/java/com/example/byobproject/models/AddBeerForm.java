package com.example.byobproject.models;

import javax.validation.constraints.NotNull;

public class AddBeerForm {

    @NotNull
    private int userId;

    @NotNull
    private int beerId;

    private Iterable<Beer> beers;

    private User user;

    public AddBeerForm() {}

    public AddBeerForm(Iterable<Beer> beers, User user) {
        this.beers = beers;
        this.user = user;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBeerId() {
        return beerId;
    }

    public void setBeerId(int cheeseId) {
        this.beerId= beerId;
    }

    public Iterable<Beer> getBeers() {
        return beers;
    }

    public User getUser() {
        return user;
    }
}
