package com.codegym.model;

public class Image_Stone {
    private Image image;
    private String stone_name;

    public Image_Stone() {
    }

    public Image_Stone(Image image) {
        this.image = image;
    }

    public Image_Stone(Image image, String stone_name) {
        this.image = image;
        this.stone_name = stone_name;
    }

    public String getStone_name() {
        return stone_name;
    }

    public void setStone_name(String stone_name) {
        this.stone_name = stone_name;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
