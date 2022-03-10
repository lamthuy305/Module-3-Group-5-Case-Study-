package com.codegym.model;

public class Image {
    private int id;
    private String link;
    private int stone_id;

    public Image() {
    }

    public Image(String link, int stone_id) {
        this.link = link;
        this.stone_id = stone_id;
    }

    public Image(int id, String link, int stone_id) {
        this.id = id;
        this.link = link;
        this.stone_id = stone_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getStone_id() {
        return stone_id;
    }

    public void setStone_id(int stone_id) {
        this.stone_id = stone_id;
    }
}
