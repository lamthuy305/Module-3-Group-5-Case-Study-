package com.codegym.model;

public class Order {
    private int id;
    private int user_id;
    private String date;

    public Order() {
    }

    public Order(int user_id, String date) {
        this.user_id = user_id;
        this.date = date;
    }

    public Order(int id, int user_id, String date) {
        this.id = id;
        this.user_id = user_id;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}