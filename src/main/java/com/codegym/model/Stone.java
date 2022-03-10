package com.codegym.model;

public class Stone {
    private int id;
    private String name;
    private double price;
    private String description;
    private String image;
    private int category_id;

    public Stone() {
    }

    public Stone(String name, double price, String description, String image, int category_id) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
        this.category_id = category_id;
    }

    public Stone(int id, String name, double price, String description, String image, int category_id) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
        this.category_id = category_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
}
