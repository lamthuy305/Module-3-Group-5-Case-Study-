package com.codegym.model;

public class Order {
    private int id;
    private int user_id;
    private int stone_id;
    private int quantity;
    private String date;

    public Order() {
    }

    public Order(int user_id, int stone_id, int quantity, String date) {
        this.user_id = user_id;
        this.stone_id = stone_id;
        this.quantity = quantity;
        this.date = date;
    }

    public Order(int id, int user_id, int stone_id, int quantity, String date) {
        this.id = id;
        this.user_id = user_id;
        this.stone_id = stone_id;
        this.quantity = quantity;
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

    public int getStone_id() {
        return stone_id;
    }

    public void setStone_id(int stone_id) {
        this.stone_id = stone_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

