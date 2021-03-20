package com.hackerrank.test.vo;

public class Dataum{
    private String city;
    private String name;
    private int estimated_cost;
    private UserRating user_rating;
    private int id;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEstimated_cost() {
        return estimated_cost;
    }

    public void setEstimated_cost(int estimated_cost) {
        this.estimated_cost = estimated_cost;
    }

    public UserRating getUser_rating() {
        return user_rating;
    }

    public void setUser_rating(UserRating user_rating) {
        this.user_rating = user_rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}